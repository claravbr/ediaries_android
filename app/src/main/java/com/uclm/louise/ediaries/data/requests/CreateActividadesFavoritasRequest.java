package com.uclm.louise.ediaries.data.requests;

import java.util.List;
import com.google.gson.annotations.SerializedName;


public class CreateActividadesFavoritasRequest {

    @SerializedName("child_id")
    private Integer childId;
    @SerializedName("actividadfavorita_ids")
    private List<Integer> actividadfavoritaIds;

    /**
     * No args constructor for use in serialization
     *
     */
    public CreateActividadesFavoritasRequest() {
    }

    /**
     *
     * @param actividadfavoritaIds
     * @param childId
     */
    public CreateActividadesFavoritasRequest(Integer childId, List<Integer> actividadfavoritaIds) {
        super();
        this.childId = childId;
        this.actividadfavoritaIds = actividadfavoritaIds;
    }

    public Integer getChildId() {
        return childId;
    }

    public void setChildId(Integer childId) {
        this.childId = childId;
    }

    public CreateActividadesFavoritasRequest withChildId(Integer childId) {
        this.childId = childId;
        return this;
    }

    public List<Integer> getActividadfavoritaIds() {
        return actividadfavoritaIds;
    }

    public void setActividadfavoritaIds(List<Integer> actividadfavoritaIds) {
        this.actividadfavoritaIds = actividadfavoritaIds;
    }

    public CreateActividadesFavoritasRequest withActividadfavoritaIds(List<Integer> actividadfavoritaIds) {
        this.actividadfavoritaIds = actividadfavoritaIds;
        return this;
    }

}