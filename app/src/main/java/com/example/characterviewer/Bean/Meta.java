package com.example.characterviewer.Bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Meta {

    @SerializedName("src_id")
    @Expose
    private Integer srcId;
    @SerializedName("tab")
    @Expose
    private String tab;
    @SerializedName("js_callback_name")
    @Expose
    private String jsCallbackName;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("repo")
    @Expose
    private String repo;
    @SerializedName("src_name")
    @Expose
    private String srcName;
    @SerializedName("is_stackexchange")
    @Expose
    private Object isStackexchange;
    @SerializedName("topic")
    @Expose
    private List<String> topic = null;
    @SerializedName("src_options")
    @Expose
    private SrcOptions srcOptions;
    @SerializedName("src_domain")
    @Expose
    private String srcDomain;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("attribution")
    @Expose
    private Object attribution;
    @SerializedName("unsafe")
    @Expose
    private Integer unsafe;
    @SerializedName("dev_date")
    @Expose
    private Object devDate;
    @SerializedName("perl_module")
    @Expose
    private String perlModule;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("dev_milestone")
    @Expose
    private String devMilestone;
    @SerializedName("blockgroup")
    @Expose
    private Object blockgroup;
    @SerializedName("live_date")
    @Expose
    private Object liveDate;
    @SerializedName("signal_from")
    @Expose
    private String signalFrom;
    @SerializedName("designer")
    @Expose
    private Object designer;
    @SerializedName("developer")
    @Expose
    private List<Developer> developer = null;
    @SerializedName("maintainer")
    @Expose
    private Maintainer maintainer;
    @SerializedName("src_url")
    @Expose
    private Object srcUrl;
    @SerializedName("created_date")
    @Expose
    private Object createdDate;
    @SerializedName("example_query")
    @Expose
    private String exampleQuery;
    @SerializedName("production_state")
    @Expose
    private String productionState;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("producer")
    @Expose
    private Object producer;

    public Integer getSrcId() {
        return srcId;
    }

    public void setSrcId(Integer srcId) {
        this.srcId = srcId;
    }

    public String getTab() {
        return tab;
    }

    public void setTab(String tab) {
        this.tab = tab;
    }

    public String getJsCallbackName() {
        return jsCallbackName;
    }

    public void setJsCallbackName(String jsCallbackName) {
        this.jsCallbackName = jsCallbackName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRepo() {
        return repo;
    }

    public void setRepo(String repo) {
        this.repo = repo;
    }

    public String getSrcName() {
        return srcName;
    }

    public void setSrcName(String srcName) {
        this.srcName = srcName;
    }

    public Object getIsStackexchange() {
        return isStackexchange;
    }

    public void setIsStackexchange(Object isStackexchange) {
        this.isStackexchange = isStackexchange;
    }

    public List<String> getTopic() {
        return topic;
    }

    public void setTopic(List<String> topic) {
        this.topic = topic;
    }

    public SrcOptions getSrcOptions() {
        return srcOptions;
    }

    public void setSrcOptions(SrcOptions srcOptions) {
        this.srcOptions = srcOptions;
    }

    public String getSrcDomain() {
        return srcDomain;
    }

    public void setSrcDomain(String srcDomain) {
        this.srcDomain = srcDomain;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getAttribution() {
        return attribution;
    }

    public void setAttribution(Object attribution) {
        this.attribution = attribution;
    }

    public Integer getUnsafe() {
        return unsafe;
    }

    public void setUnsafe(Integer unsafe) {
        this.unsafe = unsafe;
    }

    public Object getDevDate() {
        return devDate;
    }

    public void setDevDate(Object devDate) {
        this.devDate = devDate;
    }

    public String getPerlModule() {
        return perlModule;
    }

    public void setPerlModule(String perlModule) {
        this.perlModule = perlModule;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDevMilestone() {
        return devMilestone;
    }

    public void setDevMilestone(String devMilestone) {
        this.devMilestone = devMilestone;
    }

    public Object getBlockgroup() {
        return blockgroup;
    }

    public void setBlockgroup(Object blockgroup) {
        this.blockgroup = blockgroup;
    }

    public Object getLiveDate() {
        return liveDate;
    }

    public void setLiveDate(Object liveDate) {
        this.liveDate = liveDate;
    }

    public String getSignalFrom() {
        return signalFrom;
    }

    public void setSignalFrom(String signalFrom) {
        this.signalFrom = signalFrom;
    }

    public Object getDesigner() {
        return designer;
    }

    public void setDesigner(Object designer) {
        this.designer = designer;
    }

    public List<Developer> getDeveloper() {
        return developer;
    }

    public void setDeveloper(List<Developer> developer) {
        this.developer = developer;
    }

    public Maintainer getMaintainer() {
        return maintainer;
    }

    public void setMaintainer(Maintainer maintainer) {
        this.maintainer = maintainer;
    }

    public Object getSrcUrl() {
        return srcUrl;
    }

    public void setSrcUrl(Object srcUrl) {
        this.srcUrl = srcUrl;
    }

    public Object getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Object createdDate) {
        this.createdDate = createdDate;
    }

    public String getExampleQuery() {
        return exampleQuery;
    }

    public void setExampleQuery(String exampleQuery) {
        this.exampleQuery = exampleQuery;
    }

    public String getProductionState() {
        return productionState;
    }

    public void setProductionState(String productionState) {
        this.productionState = productionState;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getProducer() {
        return producer;
    }

    public void setProducer(Object producer) {
        this.producer = producer;
    }

}