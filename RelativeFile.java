import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Relative file class will check whether root path and relative file path are valid by comparing with list of valid files, and build the final one.
 * This is not a clean class - added all test and real methods in one - to just save my time :)
 */
public class RelativeFile {

    // ++++++++++++++++ Define test data. +++++++++++++++++
    // Windows test data
    // Root path for testing
    private static final String WIN_ROOT_PATH = "C:/Folder1/Folder2";

    // Key: relative path
    // Value: Expected output
    private static final HashMap<String, String> WIN_RELATIVE_PATH_OUTPUT_MAP = new HashMap<>();

    // Define some list of valid folders and files.
    private static final List<String> LINUX_WIN_VALID_FILES_AND_FOLDERS = new ArrayList<>();

    static {
        // Windows style
        WIN_RELATIVE_PATH_OUTPUT_MAP.put("test.txt", "C:/Folder1/Folder2/test.txt");
        WIN_RELATIVE_PATH_OUTPUT_MAP.put("../../test.txt", "C:/test.txt");
        WIN_RELATIVE_PATH_OUTPUT_MAP.put("../.././test.txt", "C:/test.txt");
        WIN_RELATIVE_PATH_OUTPUT_MAP.put("../valid/test.txt", "C:/Folder1/valid/test.txt");
        WIN_RELATIVE_PATH_OUTPUT_MAP.put("./test.txt", "C:/Folder1/Folder2/test.txt");
        WIN_RELATIVE_PATH_OUTPUT_MAP.put("../valid/../test.txt", "C:/Folder1/test.txt");
        WIN_RELATIVE_PATH_OUTPUT_MAP.put("../valid/../Folder2/../valid/../test.txt", "C:/Folder1/test.txt");
        WIN_RELATIVE_PATH_OUTPUT_MAP.put("../valid/../kumaran/../test.txt", "ERROR");
        WIN_RELATIVE_PATH_OUTPUT_MAP.put("../valid/../suresh/../kumaran/../test.txt", "ERROR");
        WIN_RELATIVE_PATH_OUTPUT_MAP.put("./../valid/../suresh/../kumaran/../test.txt", "ERROR");
        WIN_RELATIVE_PATH_OUTPUT_MAP.put("../Kumaran/../test.txt", "ERROR");
        WIN_RELATIVE_PATH_OUTPUT_MAP.put("valid/Kumaran/../test.txt", "ERROR");
        WIN_RELATIVE_PATH_OUTPUT_MAP.put("valid/test.txt", "ERROR");
        WIN_RELATIVE_PATH_OUTPUT_MAP.put("../../../test.txt", "ERROR");
        WIN_RELATIVE_PATH_OUTPUT_MAP.put("../../../../test.txt", "ERROR");
        WIN_RELATIVE_PATH_OUTPUT_MAP.put("./../../../../test.txt", "ERROR");

        LINUX_WIN_VALID_FILES_AND_FOLDERS.add("C:/");
        LINUX_WIN_VALID_FILES_AND_FOLDERS.add("C:/Folder1");
        LINUX_WIN_VALID_FILES_AND_FOLDERS.add("C:/Folder1/Folder2");
        LINUX_WIN_VALID_FILES_AND_FOLDERS.add("C:/Folder1/Folder2/test.txt");
        LINUX_WIN_VALID_FILES_AND_FOLDERS.add("C:/Folder1/test.txt");
        LINUX_WIN_VALID_FILES_AND_FOLDERS.add("C:/Folder1/valid");
        LINUX_WIN_VALID_FILES_AND_FOLDERS.add("C:/Folder1/valid/test.txt");
        LINUX_WIN_VALID_FILES_AND_FOLDERS.add("C:/test.txt");
    }

    // Linux test data
    // Root path for testing
    private static final String LINUX_ROOT_PATH = "/Folder1/Folder2";

    // Key: relative path
    // Value: Expected output
    private static final HashMap<String, String> LINUX_RELATIVE_PATH_OUTPUT_MAP = new HashMap<>();

    static {
        // Windows style
        LINUX_RELATIVE_PATH_OUTPUT_MAP.put("test.txt", "/Folder1/Folder2/test.txt");
        LINUX_RELATIVE_PATH_OUTPUT_MAP.put("../../test.txt", "/test.txt");
        LINUX_RELATIVE_PATH_OUTPUT_MAP.put("../.././test.txt", "/test.txt");
        LINUX_RELATIVE_PATH_OUTPUT_MAP.put("../valid/test.txt", "/Folder1/valid/test.txt");
        LINUX_RELATIVE_PATH_OUTPUT_MAP.put("./test.txt", "/Folder1/Folder2/test.txt");
        LINUX_RELATIVE_PATH_OUTPUT_MAP.put("../valid/../test.txt", "/Folder1/test.txt");
        LINUX_RELATIVE_PATH_OUTPUT_MAP.put("../valid/../Folder2/../valid/../test.txt", "/Folder1/test.txt");
        LINUX_RELATIVE_PATH_OUTPUT_MAP.put("../valid/../kumaran/../test.txt", "ERROR");
        LINUX_RELATIVE_PATH_OUTPUT_MAP.put("../valid/../suresh/../kumaran/../test.txt", "ERROR");
        LINUX_RELATIVE_PATH_OUTPUT_MAP.put("./../valid/../suresh/../kumaran/../test.txt", "ERROR");
        LINUX_RELATIVE_PATH_OUTPUT_MAP.put("../Kumaran/../test.txt", "ERROR");
        LINUX_RELATIVE_PATH_OUTPUT_MAP.put("valid/Kumaran/../test.txt", "ERROR");
        LINUX_RELATIVE_PATH_OUTPUT_MAP.put("valid/test.txt", "ERROR");
        LINUX_RELATIVE_PATH_OUTPUT_MAP.put("../../../test.txt", "ERROR");
        LINUX_RELATIVE_PATH_OUTPUT_MAP.put("../../../../test.txt", "ERROR");
        LINUX_RELATIVE_PATH_OUTPUT_MAP.put("./../../../../test.txt", "ERROR");

        LINUX_WIN_VALID_FILES_AND_FOLDERS.add("/");
        LINUX_WIN_VALID_FILES_AND_FOLDERS.add("/Folder1");
        LINUX_WIN_VALID_FILES_AND_FOLDERS.add("/Folder1/Folder2");
        LINUX_WIN_VALID_FILES_AND_FOLDERS.add("/Folder1/Folder2/test.txt");
        LINUX_WIN_VALID_FILES_AND_FOLDERS.add("/Folder1/test.txt");
        LINUX_WIN_VALID_FILES_AND_FOLDERS.add("/Folder1/valid");
        LINUX_WIN_VALID_FILES_AND_FOLDERS.add("/Folder1/valid/test.txt");
        LINUX_WIN_VALID_FILES_AND_FOLDERS.add("/test.txt");
    }
    // ++++++++++++++++ END: Define test data. +++++++++++++++++

    public static void main(String[] args) {
        // do testing for each test data criteria in the RELATIVE_PATH_OUTPUT_MAP
        for (Map.Entry<String, String> entry : WIN_RELATIVE_PATH_OUTPUT_MAP.entrySet()) {
            // Real method invocation.
            String output = getAbsolutePath(WIN_ROOT_PATH, entry.getKey());
            System.out.println(Objects.equals(output, entry.getValue()) ? "SUCCESS" : "ERROR !!!");
            System.out.println("Input: " + entry.getKey() + "\n | Expected: " + entry.getValue() + "\n | Actual: " + output);
        }
        // do testing for each test data criteria in the RELATIVE_PATH_OUTPUT_MAP
        for (Map.Entry<String, String> entry : LINUX_RELATIVE_PATH_OUTPUT_MAP.entrySet()) {
            // Real method invocation.
            String output = getAbsolutePath(LINUX_ROOT_PATH, entry.getKey());
            System.out.println(Objects.equals(output, entry.getValue()) ? "SUCCESS" : "ERROR !!!");
            System.out.println("Input: " + entry.getKey() + "\n | Expected: " + entry.getValue() + "\n | Actual: " + output);
        }
    }

    /**
     * Based on input params - builds a valid file path.
     * - Real method to be tested.
     *
     * @param rootPath     - Current Directory
     * @param relativePath - Relative to current Directory
     * @return
     */
    private static String getAbsolutePath(String rootPath, String relativePath) {
        String absolutePath = null;
        String[] rootPathArray = rootPath.split("/");
        String[] relativePathArray = relativePath.split("/");

        // define newpath - as a copy of root path
        StringBuilder newAbsolutePath = new StringBuilder(rootPath);
        int newAbsolutePathLength = rootPathArray.length;

        for (String currentPathItem : relativePathArray) {
            if ("..".equals(currentPathItem)) {
                // go back one folder - hence reduce the length by 1
                newAbsolutePathLength--;
                // Going out of path is ERRORed
                if (newAbsolutePathLength < 0) {
                    return "ERROR";
                }
            } else if (!".".equals(currentPathItem)) { // . is same as current directory
                // for every iteration build the new path array.
                String[] newPathArray = newAbsolutePath.toString().split("/");
                newAbsolutePath = new StringBuilder();
                for (int i = 0; i < newAbsolutePathLength; i++) {
                    newAbsolutePath.append(newPathArray[i]).append("/");
                }
                newAbsolutePath.append(currentPathItem);

                // compare with list of valid files and folders
                if (!LINUX_WIN_VALID_FILES_AND_FOLDERS.contains(newAbsolutePath.toString())) {
                    return "ERROR";
                } else {
                    absolutePath = newAbsolutePath.toString();
                }
                newAbsolutePathLength++;
            }
        }
        return absolutePath;
    }
}
