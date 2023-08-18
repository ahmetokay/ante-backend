package tr.com.ante.core.utils;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.springframework.http.HttpHeaders;
import tr.com.ante.enm.ExportTypeEnum;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class ReportUtils {

    public static <T> void export(HttpServletResponse response, InputStream reportStream, List<T> dataList, ExportTypeEnum exportType) {
        try {
            response.setContentType("application/x-download");

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("dataSource", new JRBeanCollectionDataSource(dataList));

            JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

            ServletOutputStream outputStream = response.getOutputStream();

            Exporter exporter = null;
            switch (exportType) {
                case PDF:
                    exporter = new JRPdfExporter();
                    exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                    exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
                    response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + "document.pdf" + "\"");
                    break;
                case WORD:
                    exporter = new JRDocxExporter();
                    exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                    exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
                    response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + "document.docx" + "\"");
                    break;
                case EXCEL:
                    exporter = new JRXlsxExporter();
                    exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                    exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
                    response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + "document.xlsx" + "\"");
                    break;
                default:
            }
            exporter.exportReport();
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
        }
    }
}