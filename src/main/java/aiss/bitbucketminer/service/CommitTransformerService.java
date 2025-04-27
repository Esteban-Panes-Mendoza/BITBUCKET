package aiss.bitbucketminer.service;

import aiss.bitbucketminer.model.Corrección.GitMinerCommit;
import aiss.bitbucketminer.model.COMMIT.Commit;
import aiss.bitbucketminer.model.COMMIT.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommitTransformerService {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

    public static List<GitMinerCommit> transform(Commit commitData, String projectName) {
        List<GitMinerCommit> gitMinerCommits = new ArrayList<>();

        if (commitData == null || commitData.getValues() == null) {
            return gitMinerCommits;
        }

        for (Value value : commitData.getValues()) {
            GitMinerCommit commit = new GitMinerCommit();

            commit.setId(value.getHash());
            commit.setTitle(value.getSummary() != null ? value.getSummary().getRaw() : value.getMessage());
            commit.setMessage(value.getMessage());

            if (value.getAuthor() != null) {
                if (value.getAuthor().getUser() != null) {
                    commit.setAuthorname(value.getAuthor().getUser().getDisplayName());
                } else {
                    commit.setAuthorname(value.getAuthor().getRaw());
                }
                //PARA SACAR SOLO EL EMAIL HAY QUE HACER UN METODO APARTE EN EL QUE META (ExtraerEmail(String raw))
                commit.setAuthor_email(value.getAuthor().getRaw());
            } else {
                commit.setAuthorname(null);
                commit.setAuthor_email(null);
            }

            if (value.getDate() != null) {
                commit.setAuthored_date(LocalDateTime.parse(value.getDate(), formatter));
            }

            if (value.getLinks() != null && value.getLinks().getHtml() != null) {
                commit.setWeb_url(value.getLinks().getHtml().getHref());
            }
            commit.setProject(projectName);
            gitMinerCommits.add(commit);



        }


        return gitMinerCommits;
    }

}
