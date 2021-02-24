package cn.zhuguangsheng.hiadvbox;

public class HiAdvItem {
    //数据库专用
    //public String uniqueId;

    //资源唯一id
    private String resourceId;

    //0--picture,  1-video
    private int resourceType;

    //time second
    private int resourceDuration;

    private String resourceUrl;

    private String localResourceFilePath;   //本地文件路径及名称

    public HiAdvItem(String resourceId, int resourceType, int resourceDuration, String localResourceFilePath) {
        this.resourceId = resourceId;
        //this.uniqueId = UUID.randomUUID().toString();
        this.resourceType = resourceType;
        this.resourceDuration = resourceDuration;
        this.localResourceFilePath = localResourceFilePath;
        //this.resourceUrl = resourceUrl;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public int getResourceType() {
        return resourceType;
    }

    public void setResourceType(int resourceType) {
        this.resourceType = resourceType;
    }

    public int getResourceDuration() {
        return resourceDuration;
    }

    public void setResourceDuration(int resourceDuration) {
        this.resourceDuration = resourceDuration;
    }

    public String getResourceUrl() {
        return resourceUrl;
    }

    public void setResourceUrl(String resourceUrl) {
        this.resourceUrl = resourceUrl;
    }

    public String getLocalResourceFilePath() {
        return localResourceFilePath;
    }

    public void setLocalResourceFilePath(String localResourceFilePath) {
        this.localResourceFilePath = localResourceFilePath;
    }

    @Override
    public String toString() {
        return "HiAdvItem{" +
                "resourceId='" + resourceId + '\'' +
                ", resourceType=" + resourceType +
                ", resourceDuration=" + resourceDuration +
                ", resourceUrl='" + resourceUrl + '\'' +
                ", localResourceFilePath='" + localResourceFilePath + '\'' +
                '}';
    }
}
