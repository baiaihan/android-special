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
//Google Auto的主要作用是注解Processor类，并对其生成META-INF的配置信息，可以让你不用去写META-INF这些配置文件，只要在自定义的Processor上面加上@AutoService(Processor.class)
@AutoService(Processor.class)
//从jdk 1.7开始，可以使用如下注解来代替getSupporedAnnotationTypes()和getSupportedSourceVersion()方法:

@SupportedAnnotationTypes("annotationprocessor.baijunyu.com.api.Factory")
public class MyProcessor extends AbstractProcessor {
    //初始化操作的方法，RoundEnvironment会提供很多有用的工具类Elements、Types和Filer等。
    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
    }

    //这相当于每个处理器的主函数main()。在该方法中去扫描、评估、处理以及生成Java文件。
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        System.out.println("-------------------Hello Wolrd,Custom Processor-----------------------");
        return false;
    }

//    //这里你必须指定，该注解器是注册给哪个注解的
//    @Override
//    public Set<String> getSupportedAnnotationTypes() {
//        return super.getSupportedAnnotationTypes();
//    }
//
    //用来指定你使用的java版本。通常这里会直接放回SourceVersion.latestSupported()
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
}
