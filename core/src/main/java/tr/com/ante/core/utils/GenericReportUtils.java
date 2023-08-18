package tr.com.ante.core.utils;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.components.ComponentsExtensionsRegistryFactory;
import net.sf.jasperreports.components.table.DesignCell;
import net.sf.jasperreports.components.table.StandardColumn;
import net.sf.jasperreports.components.table.StandardTable;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.component.ComponentKey;
import net.sf.jasperreports.engine.design.*;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.type.HorizontalTextAlignEnum;
import net.sf.jasperreports.engine.type.VerticalTextAlignEnum;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.springframework.http.HttpHeaders;
import org.springframework.util.ReflectionUtils;
import tr.com.ante.core.exception.ReportException;
import tr.com.ante.core.model.BaseModel;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class GenericReportUtils<T extends BaseModel> {

    private static final List<String> CONVERT_NOT_ALLOWED_FIELD_LIST = Arrays.asList("password");
    private static final String FIELD_PREFIX = "field";
    private static final String PARAMETER_DATA = "tableData";
    private static JasperDesign jasperDesign;

    public static <T> void export(String reportName, Class<T> tClass, List<T> dataList, HttpServletResponse response) throws ReportException {
        try {
            List<Field> fieldList = getFields(tClass);
            fieldList = fieldList.stream().filter(t -> !CONVERT_NOT_ALLOWED_FIELD_LIST.contains(t.getName())).collect(Collectors.toList());
            fieldList = fieldList.stream().filter(t -> !t.getType().equals(List.class)).collect(Collectors.toList());

            int columnCount = fieldList.size();

            response.setContentType("application/x-download");

            ServletOutputStream outputStream = response.getOutputStream();

            jasperDesign = new JasperDesign();
            jasperDesign.setName(reportName);
            jasperDesign.setWhenNoDataType(WhenNoDataTypeEnum.ALL_SECTIONS_NO_DETAIL);
            JRDesignParameter parameter = new JRDesignParameter();
            parameter.setValueClass(List.class);
            parameter.setName(PARAMETER_DATA);
            jasperDesign.addParameter(parameter);

            //the subdataset
            String datasetName = "tableDataset";
            JRDesignDataset subdataset = new JRDesignDataset(false);
            subdataset.setName(datasetName);

            // kolon sayısı kadar field ekleniyor
            for (int i = 0; i < columnCount; i++) {
                JRDesignField field = new JRDesignField();
                field.setValueClass(String.class);
                field.setName(FIELD_PREFIX + i);
                subdataset.addField(field);
            }
            jasperDesign.addDataset(subdataset);

            //the table element
            JRDesignComponentElement tableElement = new JRDesignComponentElement(jasperDesign);
            tableElement.setX(0);
            tableElement.setY(0);
            tableElement.setWidth(200);
            tableElement.setHeight(50);

            ComponentKey componentKey = new ComponentKey(ComponentsExtensionsRegistryFactory.NAMESPACE, "c", ComponentsExtensionsRegistryFactory.TABLE_COMPONENT_NAME);
            tableElement.setComponentKey(componentKey);

            StandardTable table = new StandardTable();

            //the table data source
            JRDesignDatasetRun datasetRun = new JRDesignDatasetRun();
            datasetRun.setDatasetName(datasetName);
            datasetRun.setDataSourceExpression(new JRDesignExpression("new net.sf.jasperreports.engine.data.JRMapCollectionDataSource($P{" + PARAMETER_DATA + "})"));
            table.setDatasetRun(datasetRun);

            // kolon ekleniyor
            for (int i = 0; i < columnCount; i++) {
                StandardColumn column = createColumn(100, 20, fieldList.get(i), "$F{" + FIELD_PREFIX + i + "}");
                table.addColumn(column);
            }

            tableElement.setComponent(table);

            JRDesignBand title = new JRDesignBand();
            title.setHeight(50);
            title.addElement(tableElement);
            jasperDesign.setTitle(title);

            JasperReport report = JasperCompileManager.compileReport(jasperDesign);
            Map<String, Object> params = new HashMap<>();
            params.put(PARAMETER_DATA, createTableDataFromList(tClass, fieldList, dataList));

            JasperPrint jasperPrint = JasperFillManager.fillReport(report, params);

            Exporter exporter = new JRXlsxExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + "document.xlsx" + "\"");
            exporter.exportReport();
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        }
    }

    private static <T> List<Map<String, Object>> createTableDataFromList(Class<T> tClass, List<Field> fieldList, List<T> dataList) {
        List<Map<String, Object>> data = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {

            HashMap<String, Object> rowMap = new HashMap<>();

            T row = dataList.get(i);

            Field field;
            for (int j = 0; j < fieldList.size(); j++) {
                try {
                    field = fieldList.get(j);

                    Field objField = ReflectionUtils.findField(tClass, field.getName());
                    objField.setAccessible(true);

                    rowMap.put(FIELD_PREFIX + j, createCellData(row, objField));
                } catch (IllegalAccessException e) {
                    rowMap.put(FIELD_PREFIX + j, null);
                }
            }

            data.add(rowMap);
        }

        return data;
    }

    private static <T> String createCellData(T row, Field field) throws IllegalAccessException {
        if (field.get(row) == null) {
            return null;
        }

        if (field.getType().equals(Date.class)) {
            return DateUtils.formatDate((Date) field.get(row), "dd.MM.yyyy HH:mm:ss");
        }

        return field.get(row).toString();
    }

    private static StandardColumn createColumn(int width, int height, Field field, String detailExpression) {
        StandardColumn column = new StandardColumn();
        column.setWidth(width);

        //column header
        DesignCell header = new DesignCell();
        header.setDefaultStyleProvider(jasperDesign);
        header.getLineBox().getPen().setLineWidth(1f);
        header.setHeight(height);

        JRDesignStaticText headerElement = new JRDesignStaticText(jasperDesign);
        headerElement.setX(0);
        headerElement.setY(0);
        headerElement.setBold(true);
        headerElement.setVerticalTextAlign(VerticalTextAlignEnum.MIDDLE);
        headerElement.setHorizontalTextAlign(HorizontalTextAlignEnum.CENTER);
        headerElement.setWidth(width);
        headerElement.setHeight(height);
        headerElement.setText(field.getName());

        header.addElement(headerElement);
        column.setColumnHeader(header);

        //column detail
        DesignCell detail = new DesignCell();
        detail.setDefaultStyleProvider(jasperDesign);
        detail.getLineBox().getPen().setLineWidth(1f);
        detail.setHeight(height);

        JRDesignTextField detailElement = new JRDesignTextField(jasperDesign);
        detailElement.setX(0);
        detailElement.setY(0);
        detailElement.setWidth(width);
        detailElement.setHeight(height);
        detailElement.setBlankWhenNull(true);
        detailElement.setVerticalTextAlign(VerticalTextAlignEnum.MIDDLE);
        detailElement.setHorizontalTextAlign(HorizontalTextAlignEnum.CENTER);
        detailElement.setExpression(new JRDesignExpression(detailExpression));

        detail.addElement(detailElement);
        column.setDetailCell(detail);

        return column;
    }

    private static List<Field> getFields(Class<?> type) {
        List<Field> result = new ArrayList<Field>();

        Class<?> i = type;
        while (i != null && i != Object.class) {
            Collections.addAll(result, i.getDeclaredFields());
            i = i.getSuperclass();
        }

        return result;
    }
}