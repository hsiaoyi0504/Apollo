package org.bbop.apollo.gwt.client.dto.assemblage;

import com.google.gwt.json.client.JSONBoolean;
import com.google.gwt.json.client.JSONNumber;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import org.bbop.apollo.gwt.client.dto.SequenceInfo;
import org.bbop.apollo.gwt.shared.FeatureStringEnum;

/**
 * Created by ndunn on 9/30/15.
 */
public class AssemblageSequence extends JSONObject {

    public AssemblageSequence() {
    }

    public AssemblageSequence(JSONObject jsonObject) {
        this.put(FeatureStringEnum.NAME.getValue(), new JSONString(jsonObject.get(FeatureStringEnum.NAME.getValue()).isString().stringValue()));
        if (jsonObject.containsKey(FeatureStringEnum.START.getValue())) {
            this.put(FeatureStringEnum.START.getValue(), new JSONNumber(jsonObject.get(FeatureStringEnum.START.getValue()).isNumber().doubleValue()));
            this.put(FeatureStringEnum.END.getValue(), new JSONNumber(jsonObject.get(FeatureStringEnum.END.getValue()).isNumber().doubleValue()));
        }
        if (jsonObject.containsKey(FeatureStringEnum.REVERSE.getValue())) {
            this.put(FeatureStringEnum.REVERSE.getValue(), jsonObject.get(FeatureStringEnum.REVERSE.getValue()));
        } else {
            this.put(FeatureStringEnum.REVERSE.getValue(), JSONBoolean.getInstance(false));
        }
        if (jsonObject.containsKey(FeatureStringEnum.FEATURE.getValue())) {
            SequenceFeatureInfo sequenceFeatureInfo = new SequenceFeatureInfo(jsonObject.get(FeatureStringEnum.FEATURE.getValue()).isObject());
            this.put(
                    FeatureStringEnum.FEATURE.getValue()
                    , sequenceFeatureInfo
            );
        }
//        if (jsonObject.containsKey(FeatureStringEnum.FEATURES.getValue())) {
//            this.put(FeatureStringEnum.FEATURES.getValue(), jsonObject.get(FeatureStringEnum.FEATURES.getValue()).isArray());
//        }
    }

    public AssemblageSequence(SequenceInfo sequenceInfo) {
        setStart(sequenceInfo.getStart());
        setEnd(sequenceInfo.getEnd());
        setName(sequenceInfo.getName());
    }

    public Boolean getReverse(){
        if(containsKey(FeatureStringEnum.REVERSE.getValue()) && get(FeatureStringEnum.REVERSE.getValue()).isBoolean()!=null){
            return get(FeatureStringEnum.REVERSE.getValue()).isBoolean().booleanValue();
        }
        return false ;
    }

    public void setReverse(Boolean reverse){
        put(FeatureStringEnum.REVERSE.getValue(),JSONBoolean.getInstance(reverse));
    }

    public Long getStart() {
        return (long) get(FeatureStringEnum.START.getValue()).isNumber().doubleValue();
    }

    public void setStart(Long start) {
        put(FeatureStringEnum.START.getValue(), new JSONNumber(start));
    }

    public Long getEnd() {
        return (long) get(FeatureStringEnum.END.getValue()).isNumber().doubleValue();
    }

    public void setEnd(Long end) {
        put(FeatureStringEnum.END.getValue(), new JSONNumber(end));
    }


    public String getName() {
        return get(FeatureStringEnum.NAME.getValue()).isString().stringValue();
    }

    public SequenceFeatureInfo getFeature() {
        if (containsKey(FeatureStringEnum.FEATURE.getValue())) {
            return new SequenceFeatureInfo(get(FeatureStringEnum.FEATURE.getValue()).isObject());
        }
        return null;
    }

    public void setFeatureProperties(SequenceFeatureInfo sequenceFeatureInfo) {
        put(FeatureStringEnum.FEATURE.getValue(),sequenceFeatureInfo);
        put(FeatureStringEnum.START.getValue(),new JSONNumber(sequenceFeatureInfo.getStart()));
        put(FeatureStringEnum.END.getValue(),new JSONNumber(sequenceFeatureInfo.getEnd()));
    }

    public void setName(String groupName) {
        put(FeatureStringEnum.NAME.getValue(), new JSONString(groupName));
    }

    public Long getLength() {
        return getEnd() - getStart();
    }

    public AssemblageSequence deepCopy() {

        AssemblageSequence assemblageSequence = new AssemblageSequence();
        for(String key :this.keySet()){
            assemblageSequence.put(key,this.get(key));
        }

        return assemblageSequence;
    }
}
