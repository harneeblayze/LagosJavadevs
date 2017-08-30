package com.example.android.lagosjavadevs.dataclasses;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Result implements Serializable, Parcelable
{

    @SerializedName("total_count")
    @Expose
    private Integer totalCount;
    @SerializedName("incomplete_results")
    @Expose
    private Boolean incompleteResults;
    @SerializedName("items")
    @Expose
    private List<Item> items = null;
    public final static Parcelable.Creator<Result> CREATOR = new Creator<Result>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Result createFromParcel(Parcel in) {
            Result instance = new Result();
            instance.totalCount = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.incompleteResults = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
            in.readList(instance.items, (com.example.android.lagosjavadevs.dataclasses.Item.class.getClassLoader()));
            return instance;
        }

        public Result[] newArray(int size) {
            return (new Result[size]);
        }

    }
            ;
    private final static long serialVersionUID = 622752505986534219L;

    /**
     * No args constructor for use in serialization
     *
     */
    public Result() {
    }

    /**
     *
     * @param items
     * @param totalCount
     * @param incompleteResults
     */
    public Result(Integer totalCount, Boolean incompleteResults, List<Item> items) {
        super();
        this.totalCount = totalCount;
        this.incompleteResults = incompleteResults;
        this.items = items;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Boolean getIncompleteResults() {
        return incompleteResults;
    }

    public void setIncompleteResults(Boolean incompleteResults) {
        this.incompleteResults = incompleteResults;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(totalCount);
        dest.writeValue(incompleteResults);
        dest.writeList(items);
    }

    public int describeContents() {
        return 0;
    }

}