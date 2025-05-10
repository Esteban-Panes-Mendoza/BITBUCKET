package aiss.bitbucketminer.service;

import aiss.bitbucketminer.model.Corrección.GitMinerComment;
import aiss.bitbucketminer.model.Corrección.GitMinerIssues;
import aiss.bitbucketminer.model.Corrección.GitMinerUser;
import aiss.bitbucketminer.model.ISSUES.Issues;
import aiss.bitbucketminer.model.ISSUES.Value;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IssuesTransformerService {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

    public static List<GitMinerIssues> transform(Issues issuesData, List<GitMinerComment> allComments, List<GitMinerUser> allUsers) {
        List<GitMinerIssues> resultIssues = new ArrayList<>();

        if (issuesData == null || issuesData.getValues() == null) {
            return resultIssues;
        }
        Map<String, GitMinerUser> userMap = new HashMap<>();
        for (GitMinerUser user : allUsers) {
            userMap.put(user.getId(), user);
        }


        for (Value value : issuesData.getValues()) {

            GitMinerIssues issue = new GitMinerIssues();

            issue.setId(value.getId().toString());
            issue.setTitle(value.getTitle());
            issue.setDescription(value.getContent().getRaw());
            issue.setState(value.getState());
            issue.setCreated_at(value.getCreatedOn());
            issue.setUpdated_at(value.getUpdatedOn());
            issue.setClosed_at("closed".equalsIgnoreCase(value.getState()) ? issue.getUpdated_at() : null);

            List<String> labels = new ArrayList<>();
            labels.add(value.getKind());
            issue.setLabels(labels);
            issue.setVotes(value.getVotes());

            // Filtrar los comentarios correspondientes a esta issue
            List<GitMinerComment> filteredComments = new ArrayList<>();
            for (GitMinerComment c : allComments) {
                if (c.getIssueId() != null && c.getIssueId().equals(issue.getId())) {
                    filteredComments.add(c);
                }
            }
            issue.setComments(filteredComments);

            // Establecer el autor
            GitMinerUser author = userMap.get(value.getReporter().getAccountId());
            issue.setAuthor(author);
            issue.setAssignee(author);

            // Agregar la issue transformada a la lista de resultados
            resultIssues.add(issue);
        }

        return resultIssues;
    }

}
