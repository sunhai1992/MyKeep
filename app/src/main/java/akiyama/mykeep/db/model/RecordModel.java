package akiyama.mykeep.db.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import akiyama.mykeep.util.DataProviderHelper;

/**
 * FIXME
 *
 * @author zhiwu_yan
 * @version 1.0
 * @since 2015-07-07  15:30
 */
public class RecordModel extends BaseModel{

    public static final String DEFAULT_COLOR="#FFFFFF";
    public static final int DEFAULT_VALUE=-1;

    public static final String NORMAL_COLOR="#E0E0E0";
    public static final int NORMAL_VALUE= 0;

    public static final String X_NORMAL_COLOR="#CFD8DE";
    public static final int X_NORMAL_VALUE= 1;

    public static final String XX_NORMAL_COLOR="#FFD180";
    public static final int XX_NORMAL_VALUE= 2;

    public static final String IMPORTANT="#FFFF8D";
    public static final int IMPORTANT_VALUE=3;

    public static final String X_IMPORTANT="#80D8FF";
    public static final int X_IMPORTANT_VALUE=4;

    public static final String XX_IMPORTANT="#FF8A80";
    public static final int XX_IMPORTANT_VALUE=5;

    public static final int RECORD_TYPE_NORMAL = 0;
    public static final int RECORD_TYPE_LIST = 1;

    private String title;
    private String content;
    private String alarmTime;
    private int level;
    private String userId;
    private String labelNames;//里面有多条label记录，使用","隔开
    private int recordType;//记事类型
    public RecordModel(){

    }

    public RecordModel(Parcel in){
        readBase(in);
        title=in.readString();
        content=in.readString();
        alarmTime=in.readString();
        level=in.readInt();
        userId=in.readString();
        labelNames =in.readString();
        recordType = in.readInt();
    }

    @Override
    public ContentValues values() {
        ContentValues cv=convert();
        cv.put(RecordColumns.TITLE,title);
        cv.put(RecordColumns.CONTENT,content);
        cv.put(RecordColumns.ALARMTIME,alarmTime);
        cv.put(RecordColumns.LEVEL,level);
        cv.put(RecordColumns.USERID,userId);
        cv.put(RecordColumns.LABELNAMES, labelNames);
        cv.put(RecordColumns.RECORDTYPE, recordType);
        return cv;
    }

    @Override
    public Uri getContentUri() {
        return RecordColumns.CONTENT_URI;
    }

    @Override
    public String getTable() {
        return RecordColumns.TABLE_NAME;
    }

    @Override
    public RecordModel getModel(Cursor cursor) {
        if(cursor==null){
            return null;
        }
        RecordModel recordModel=new RecordModel();
        recordModel.id= DataProviderHelper.parseString(cursor,BaseColumns._ID);
        recordModel.title=DataProviderHelper.parseString(cursor,RecordColumns.TITLE);
        recordModel.content=DataProviderHelper.parseString(cursor,RecordColumns.CONTENT);
        recordModel.alarmTime=DataProviderHelper.parseString(cursor,RecordColumns.ALARMTIME);
        recordModel.level=DataProviderHelper.parseInt(cursor,RecordColumns.LEVEL);
        recordModel.createTime =DataProviderHelper.parseString(cursor,BaseColumns.CREATAT);
        recordModel.updateTime=DataProviderHelper.parseString(cursor,BaseColumns.UPDATEAT);
        recordModel.userId=DataProviderHelper.parseString(cursor,RecordColumns.USERID);
        recordModel.labelNames =DataProviderHelper.parseString(cursor,RecordColumns.LABELNAMES);
        recordModel.recordType =DataProviderHelper.parseInt(cursor, RecordColumns.RECORDTYPE);
        return recordModel;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        writeBase(dest,flags);
        dest.writeString(title);
        dest.writeString(content);
        dest.writeString(alarmTime);
        dest.writeInt(level);
        dest.writeString(userId);
        dest.writeString(labelNames);
        dest.writeInt(recordType);
    }

    public static final Parcelable.Creator<RecordModel> CREATOR = new Creator(){

        @Override
        public RecordModel createFromParcel(Parcel source) {
            RecordModel p = new RecordModel(source);
            return p;
        }

        @Override
        public RecordModel[] newArray(int size) {
            return new RecordModel[size];
        }
    };

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setLevel(String level) {
        if(level.equals(NORMAL_COLOR)){
            this.level = NORMAL_VALUE;
        }else if(level.equals(X_NORMAL_COLOR)){
            this.level = X_NORMAL_VALUE;
        }else if(level.equals(XX_NORMAL_COLOR)){
            this.level = XX_NORMAL_VALUE;
        }else if(level.equals(IMPORTANT)){
            this.level = IMPORTANT_VALUE;
        }else if(level.equals(X_IMPORTANT)){
            this.level = X_IMPORTANT_VALUE;
        }else if(level.equals(XX_IMPORTANT)){
            this.level = XX_IMPORTANT_VALUE;
        }else {
            this.level = DEFAULT_VALUE;
        }
    }

    public String getLevel() {
        if(level == NORMAL_VALUE){
            return NORMAL_COLOR;
        }else if(level == X_NORMAL_VALUE){
            return X_NORMAL_COLOR;
        }else if(level == XX_NORMAL_VALUE){
            return XX_NORMAL_COLOR;
        }else if(level == IMPORTANT_VALUE){
            return IMPORTANT;
        }else if(level == X_IMPORTANT_VALUE){
            return X_IMPORTANT;
        }else if(level == XX_IMPORTANT_VALUE){
            return XX_IMPORTANT;
        }
        return DEFAULT_COLOR;
    }

    public void setAlarmTime(String alarmTime) {
        this.alarmTime = alarmTime;
    }

    public String getAlarmTime() {
        return alarmTime;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setLabelNames(String labelNames) {
        this.labelNames = labelNames;
    }

    public String getLabelNames() {
        return labelNames;
    }

    public int getRecordType() {
        return recordType;
    }

    public void setRecordType(int recordType) {
        this.recordType = recordType;
    }
}
