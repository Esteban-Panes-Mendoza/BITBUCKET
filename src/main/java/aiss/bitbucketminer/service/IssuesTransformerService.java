package aiss.bitbucketminer.service;

import aiss.bitbucketminer.model.Corrección.GitMinerIssues;
import aiss.bitbucketminer.model.ISSUES.Issues;
import aiss.bitbucketminer.model.ISSUES.Value;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class IssuesTransformerService {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

    public static List<GitMinerIssues> transform(Issues issuesData){
        List<GitMinerIssues> res= new java.util.ArrayList<>();
         if (issuesData==null || issuesData.getValues()==null){
             return res;
         }
         for (Value value : issuesData.getValues()){
                GitMinerIssues issues= new GitMinerIssues();
                issues.setId(value.getId());
                issues.setTitle(value.getTitle());
                issues.setDescription(value.getContent().getRaw());
                issues.setState(value.getState());
                issues.setCreated_at(LocalDateTime.parse(value.getCreatedOn(), formatter));
                issues.setUpdated_at(LocalDateTime.parse(value.getUpdatedOn(), formatter));
                if (issues.getState()=="closed"){
                    issues.setClosed_at(issues.getUpdated_at());
                }else{
                    issues.setClosed_at(null);
                }
                //TODO NO ESTÁ EN EL JSON
                List<String> etiquetas= new java.util.ArrayList<>();
                etiquetas.add(value.getKind());
                issues.setLabels(etiquetas);
                issues.setVotes(value.getVotes());
                //TODO CREAR COMMENTS
                issues.setComments(new ArrayList<>());
                res.add(issues);
         }
         return res;
    }

}
