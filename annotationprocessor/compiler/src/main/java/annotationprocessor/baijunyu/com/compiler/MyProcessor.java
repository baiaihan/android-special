package annotationprocessor.baijunyu.com.compiler;

import com.google.auto.service.AutoService;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;

/**
 * Created by 95190 on 2018/7/10.
 */
//Google Auto����Ҫ������ע��Processor�࣬����������META-INF��������Ϣ���������㲻��ȥдMETA-INF��Щ�����ļ���ֻҪ���Զ����Processor�������@AutoService(Processor.class)
@AutoService(Processor.class)
//��jdk 1.7��ʼ������ʹ������ע��������getSupporedAnnotationTypes()��getSupportedSourceVersion()����:

@SupportedAnnotationTypes("annotationprocessor.baijunyu.com.api.Factory")
public class MyProcessor extends AbstractProcessor {
    //��ʼ�������ķ�����RoundEnvironment���ṩ�ܶ����õĹ�����Elements��Types��Filer�ȡ�
    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
    }

    //���൱��ÿ����������������main()���ڸ÷�����ȥɨ�衢�����������Լ�����Java�ļ���
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        System.out.println("-------------------Hello Wolrd,Custom Processor-----------------------");
        return false;
    }

//    //���������ָ������ע������ע����ĸ�ע���
//    @Override
//    public Set<String> getSupportedAnnotationTypes() {
//        return super.getSupportedAnnotationTypes();
//    }
//
    //����ָ����ʹ�õ�java�汾��ͨ�������ֱ�ӷŻ�SourceVersion.latestSupported()
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
}
