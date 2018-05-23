package ro.infoiasi.ip.easylearn.submission.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ro.infoiasi.ip.easylearn.compiler.SourceFile;
import ro.infoiasi.ip.easylearn.submission.model.Submission;
import ro.infoiasi.ip.easylearn.submission.repository.api.SubmissionRepository;
import ro.infoiasi.ip.easylearn.submission.repository.utils.SubmissionMapper;
import ro.infoiasi.ip.easylearn.utils.RunState;

import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class MysqlSubmissionRepository implements SubmissionRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Long save(Submission submission) {
        String insertQuery = "insert into submissions(userId, problemId, mainSource, language, date, state) values (?, ?, ?, ?, ?, ?)"; // submission ID is auto_increment

        jdbcTemplate.update(insertQuery,
                submission.getUserId(),
                submission.getProblemId(),
                submission.getMainSource(),
                submission.getLanguage().toString(),
                new SimpleDateFormat("YYYY-MM-dd HH-MM-ss").format(submission.getDate()),
                submission.getState().toString());

        Long id = jdbcTemplate.queryForObject("select max(id) from submissions;", Long.class);

        for (SourceFile source : submission.getSources()) {
            jdbcTemplate.update("insert into submission_code(submissionId, fileName, sourceCode) values (?,?,?)",
                    id,
                    source.getFileName(),
                    source.getContent());
        }

        return id;
    }

    @Override
    public Submission findById(Long id) {
        String mysql = "SELECT * FROM submissions where id='" + id + "'";
        List<Submission> submission = jdbcTemplate.query(mysql, new SubmissionMapper());
        return submission.get(0);
    }

    @Override
    public List<Submission> findAll() {
        String mysql = "SELECT * FROM submissions";
        return jdbcTemplate.query(mysql, new SubmissionMapper());
    }

    @Override
    public List<Submission> findByState(RunState state) {
        String mysql = "SELECT * FROM submissions where state='" + state.toString() + "'";
        return jdbcTemplate.query(mysql, new SubmissionMapper());
    }

    @Override
    public Long update(Submission submission) {
        String updateQuery = "update submissions set state='" + submission.getState() +"'";
        jdbcTemplate.update(updateQuery);
        return submission.getId();
    }
}
