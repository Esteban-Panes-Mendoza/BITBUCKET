package aiss.bitbucketminer.service;

import aiss.bitbucketminer.model.COMMENT.Comments;
import aiss.bitbucketminer.model.COMMENT.Value;
import aiss.bitbucketminer.model.Corrección.GitMinerComment;
import aiss.bitbucketminer.model.Corrección.GitMinerUser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CommentsTransformService {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

    public static GitMinerComment transform(Value value, Integer issuesId, GitMinerUser user) {
        if (value == null || value.getIssue() == null || !value.getIssue().getId().equals(issuesId)) {
            return null;
        }

        GitMinerComment comment = new GitMinerComment();
        comment.setId(value.getId().toString());
        comment.setBody(value.getContent().getRaw()==null?"Este comentario está vacío":value.getContent().getRaw());
        comment.setCreated_at(String.valueOf(value.getCreatedOn()));
        comment.setUpdated_at((String) value.getUpdatedOn());
        comment.setIssueId(value.getIssue().getId());
        comment.setAuthor(user);

        return comment;
    }


}
