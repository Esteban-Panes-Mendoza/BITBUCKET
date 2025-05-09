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

    public static List<GitMinerCommit> transform(Commit commitData) {
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
                    commit.setAuthor_name(value.getAuthor().getUser().getDisplayName());
                } else {
                    commit.setAuthor_name(value.getAuthor().getRaw());
                }

                //Metodo para extraer el email
                String email= extractContent(value.getAuthor().getRaw());
                commit.setAuthor_email(email);
            } else {
                commit.setAuthor_name(null);
                commit.setAuthor_email(null);
            }

            if (value.getDate() != null) {
                commit.setAuthored_date(LocalDateTime.parse(value.getDate(), formatter));
            }

            if (value.getLinks() != null && value.getLinks().getHtml() != null) {
                commit.setWeb_url(value.getLinks().getHtml().getHref());
            }
            gitMinerCommits.add(commit);



        }


        return gitMinerCommits;
    }

    public static String extractContent(String input) {
        StringBuilder result = new StringBuilder();
        int start = 0;
        while ((start = input.indexOf('<', start)) != -1) {
            int end = input.indexOf('>', start);
            if (end != -1) {
                String tag = input.substring(start + 1, end);
                result.append(tag);
                start = end + 1;
            } else {
                break;
            }
        }
        return result.toString();
    }


}
