package tr.com.ante.security.utils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class PermissionUtils {

    // permission yapisi XX.01.01 seklinde olabilir. Ornegin UM.01.01 -> Kullanici yonetimi liste
    public static boolean checkPermission(List<String> authorityList, String permission) {
        List<String> fields = new LinkedList<>(Arrays.asList(permission.split("\\.")));

        boolean exist = authorityList.stream().anyMatch(t -> t.equalsIgnoreCase(permission));
        if (!exist) {
            // sonraki alan yildizlanarak kontrol edilecek, eger yildizli ise bir uste gecilecek

            if (fields.size() == 2) {
                return false;
            }

            if (fields.get(fields.size() - 1).equals("*")) {
                fields.remove(fields.size() - 1);
                fields.set(fields.size() - 1, "*");
            } else {
                fields.set(fields.size() - 1, "*");
            }

            return checkPermission(authorityList, String.join(".", fields));
        }

        return true;
    }
}
