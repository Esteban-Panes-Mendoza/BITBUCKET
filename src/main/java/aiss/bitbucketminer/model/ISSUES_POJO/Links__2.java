
package aiss.bitbucketminer.model.ISSUES_POJO;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@JsonPropertyOrder({
    "self",
    "avatar",
    "html"
})
public class Links__2 {

    @JsonProperty("self")
    private Self__2 self;
    @JsonProperty("avatar")
    private Avatar__1 avatar;
    @JsonProperty("html")
    private Html__2 html;

    @JsonProperty("self")
    public Self__2 getSelf() {
        return self;
    }

    @JsonProperty("self")
    public void setSelf(Self__2 self) {
        this.self = self;
    }

    @JsonProperty("avatar")
    public Avatar__1 getAvatar() {
        return avatar;
    }

    @JsonProperty("avatar")
    public void setAvatar(Avatar__1 avatar) {
        this.avatar = avatar;
    }

    @JsonProperty("html")
    public Html__2 getHtml() {
        return html;
    }

    @JsonProperty("html")
    public void setHtml(Html__2 html) {
        this.html = html;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Links__2 .class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("self");
        sb.append('=');
        sb.append(((this.self == null)?"<null>":this.self));
        sb.append(',');
        sb.append("avatar");
        sb.append('=');
        sb.append(((this.avatar == null)?"<null>":this.avatar));
        sb.append(',');
        sb.append("html");
        sb.append('=');
        sb.append(((this.html == null)?"<null>":this.html));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
