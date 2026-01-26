package com.niuwenyu.springframework.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wenyuniu
 */
public class PropertyValues {
    ArrayList<PropertyValue> propertyValueArrayList;

    public PropertyValues() {
        propertyValueArrayList = new ArrayList<>();
    }

    public void addPropertyValue(PropertyValue pv){
        propertyValueArrayList.add(pv);
    }

    public List<PropertyValue> getPropertyValues(){
        return propertyValueArrayList;
    }

}
