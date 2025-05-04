package aiss.bitbucketminer.service;

import aiss.bitbucketminer.model.COMMENT.Comments;
import aiss.bitbucketminer.model.COMMENT.Value;
import aiss.bitbucketminer.model.Corrección.GitMinerComment;
import aiss.bitbucketminer.model.Corrección.GitMinerUser;
import aiss.bitbucketminer.model.USER.Users;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class UserTransformerService {

    public static GitMinerUser transform(Users userdata) {

        GitMinerUser user= new GitMinerUser();
            user.setId(userdata.getAccountId());
            user.setUsername(userdata.getNickname());
            user.setName(userdata.getDisplayName());
            user.setAvatar_url(userdata.getLinks().getAvatar().getHref());
            user.setWeb_url(userdata.getLinks().getSelf().getHref());

            return user;
}
}
