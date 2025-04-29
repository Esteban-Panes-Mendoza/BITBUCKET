package aiss.bitbucketminer.service;

import aiss.bitbucketminer.model.COMMENT.Comments;
import aiss.bitbucketminer.model.COMMENT.Value;
import aiss.bitbucketminer.model.Corrección.GitMinerComment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CommentsTransformService {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

    public static List<GitMinerComment> transform(Comments commentsdata) {
        List<GitMinerComment> res= new java.util.ArrayList<>();
        if (commentsdata==null || commentsdata.getValues()==null){
            return res;
        }
        for (Value value : commentsdata.getValues()){
            GitMinerComment comments= new GitMinerComment();
            comments.setId(value.getId());
            comments.setBody(value.getContent().getRaw());
            comments.setCreated_at(LocalDateTime.parse(value.getCreatedOn(), formatter));
            comments.setUpdated_at(LocalDateTime.parse(value.getUpdatedOn(), formatter));
            res.add(comments);
        }
        return res;
    }


}
