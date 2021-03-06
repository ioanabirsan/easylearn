package ro.infoiasi.ip.easylearn.submission.repository.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.Nullable;
import ro.infoiasi.ip.easylearn.submission.model.Submission;
import ro.infoiasi.ip.easylearn.utils.Language;
import ro.infoiasi.ip.easylearn.utils.SubmissionState;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SubmissionMapper implements RowMapper <Submission> {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Nullable
    @Override
    public Submission mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Submission submission = new Submission();
        submission.setId(resultSet.getLong("id"));
        submission.setUserId(resultSet.getLong("userId"));
        submission.setProblemId(resultSet.getLong("problemId"));
        submission.setMainSource(resultSet.getString("mainSource"));
        submission.setLanguage(Language.valueOf(resultSet.getString("language")));
        submission.setDate(resultSet.getDate("date"));
        submission.setState(SubmissionState.valueOf(resultSet.getString("state")));
        submission.setCompileOut(resultSet.getString("compileOut"));
        return submission;
    }
}

