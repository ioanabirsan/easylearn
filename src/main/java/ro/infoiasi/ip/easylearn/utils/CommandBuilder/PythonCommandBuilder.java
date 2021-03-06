package ro.infoiasi.ip.easylearn.utils.CommandBuilder;

import ro.infoiasi.ip.easylearn.compiler.SourceFile;

import java.util.List;

import static ro.infoiasi.ip.easylearn.utils.FileManager.getCurrentWorkingDirectory;
import static ro.infoiasi.ip.easylearn.utils.FileManager.getFilePathSeparator;

public class PythonCommandBuilder extends CommandBuilder {
    @Override
    public String buildCompileCommand(List<SourceFile> sources, String rootDirectoryPath) {
        return "";
    }

    @Override
    public String buildRunCommand(String rootDirectoryPath, String mainSource) {
        return "python "
                + getCurrentWorkingDirectory()
                + getFilePathSeparator() + rootDirectoryPath
                + getFilePathSeparator() + mainSource;
    }
}
