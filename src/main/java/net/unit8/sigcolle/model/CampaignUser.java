package net.unit8.sigcolle.model;

import java.io.Serializable;

import lombok.Data;
import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;

/**
 * @author blackawa
 */
@Entity
@Data
public class CampaignUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long campaignId;
    private String title;
    private String statement;
    private Long goal;
    private Long createUserId;
    private String firstName;
    private String lastName;

    /**
     * キャンペーンの作成者名を返却する.
     * @return キャンペーンの作成者名
     */
    public String getCreatorName() {
        return lastName + " " + firstName;
    }
}
