
package aiss.bitbucketminer.model.ISSUES_POJO;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonPropertyOrder({
    "self",
    "html",
    "comments",
    "attachments",
    "watch",
    "vote"
})
public class Links__1 {

    @JsonProperty("self")
    private Self__1 self;
    @JsonProperty("html")
    private Html__1 html;
    @JsonProperty("comments")
    private Comments comments;
    @JsonProperty("attachments")
    private Attachments attachments;
    @JsonProperty("watch")
    private Watch watch;
    @JsonProperty("vote")
    private Vote vote;

    @JsonProperty("self")
    public Self__1 getSelf() {
        return self;
    }

    @JsonProperty("self")
    public void setSelf(Self__1 self) {
        this.self = self;
    }

    @JsonProperty("html")
    public Html__1 getHtml() {
        return html;
    }

    @JsonProperty("html")
    public void setHtml(Html__1 html) {
        this.html = html;
    }

    @JsonProperty("comments")
    public Comments getComments() {
        return comments;
    }

    @JsonProperty("comments")
    public void setComments(Comments comments) {
        this.comments = comments;
    }

    @JsonProperty("attachments")
    public Attachments getAttachments() {
        return attachments;
    }

    @JsonProperty("attachments")
    public void setAttachments(Attachments attachments) {
        this.attachments = attachments;
    }

    @JsonProperty("watch")
    public Watch getWatch() {
        return watch;
    }

    @JsonProperty("watch")
    public void setWatch(Watch watch) {
        this.watch = watch;
    }

    @JsonProperty("vote")
    public Vote getVote() {
        return vote;
    }

    @JsonProperty("vote")
    public void setVote(Vote vote) {
        this.vote = vote;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Links__1 .class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("self");
        sb.append('=');
        sb.append(((this.self == null)?"<null>":this.self));
        sb.append(',');
        sb.append("html");
        sb.append('=');
        sb.append(((this.html == null)?"<null>":this.html));
        sb.append(',');
        sb.append("comments");
        sb.append('=');
        sb.append(((this.comments == null)?"<null>":this.comments));
        sb.append(',');
        sb.append("attachments");
        sb.append('=');
        sb.append(((this.attachments == null)?"<null>":this.attachments));
        sb.append(',');
        sb.append("watch");
        sb.append('=');
        sb.append(((this.watch == null)?"<null>":this.watch));
        sb.append(',');
        sb.append("vote");
        sb.append('=');
        sb.append(((this.vote == null)?"<null>":this.vote));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
