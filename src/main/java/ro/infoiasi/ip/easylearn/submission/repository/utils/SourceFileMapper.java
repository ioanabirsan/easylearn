package ro.infoiasi.ip.easylearn.submission.repository.utils;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import ro.infoiasi.ip.easylearn.compiler.SourceFile;
import ro.infoiasi.ip.easylearn.submission.model.Run;
import ro.infoiasi.ip.easylearn.utils.RunState;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SourceFileMapper implements RowMapper<SourceFile> {
    @Nullable
    @Override
    public SourceFile mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        SourceFile source = new SourceFile();

        source.setId(resultSet.getLong("id"));

        source.setSubmissionID(resultSet.getLong("submissionID"));

        source.setTitle(resultSet.getString("class_name"));

        source.setContent(resultSet.getString("sourceCode"));

        return source;
    }
}
