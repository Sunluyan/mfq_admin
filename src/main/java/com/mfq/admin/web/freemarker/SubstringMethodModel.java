package com.mfq.admin.web.freemarker;

import java.util.List;

import freemarker.template.SimpleScalar;
import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

/**                         截断使用说明 ，第一个参数是要截取的原串，第二个为要截断的最大个数，第三个参数是截断后要补充单位字符。(可填，可不填）
*
*/
public class SubstringMethodModel implements TemplateMethodModel {
   @Override
   public Object exec(List arguments) throws TemplateModelException {
       String postfix="";
       String source = (String) arguments.get(0);
       Integer subLength = Integer.parseInt((String) arguments.get(1));
       if (source == null) {
           return new SimpleScalar("");
       }
       if(arguments.size()>2){
           postfix= (String) arguments.get(2);
       }
       if(arguments.size()<=3 || !"true".equalsIgnoreCase((String)arguments.get(3))) {
           source = source.trim().replaceAll("<[^>]*>", "");
       }
       source = source.trim();
       if (source.length() <= subLength+postfix.length()   ) {
           return new SimpleScalar(source);
       } else {
           return new SimpleScalar(source.substring(0, subLength)+postfix);
       }
   }
}
