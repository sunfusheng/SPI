package com.sunfusheng.spi.compiler;

import com.google.auto.service.AutoService;
import com.sunfusheng.spi.annotation.Provide;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.MirroredTypeException;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

/**
 * @author by sunfusheng on 2019/3/14
 */
@AutoService(Processor.class)
public class ProvideProcessor extends AbstractProcessor {

    private Filer mFiler; // 文件相关的辅助类
    private Elements mElementUtils; // 元素相关的辅助类
    private Messager mMessager; // 日志相关的辅助类

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        mFiler = processingEnv.getFiler();
        mElementUtils = processingEnv.getElementUtils();
        mMessager = processingEnv.getMessager();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Collections.singleton(Provide.class.getCanonicalName());
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnvironment) {
        if (annotations != null && !annotations.isEmpty()) {
            Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(Provide.class);
            if (elements != null && !elements.isEmpty()) {

                for (Element element : elements) {
                    TypeElement typeElement = (TypeElement) element;
                    String packageName = mElementUtils.getPackageOf(element).getQualifiedName().toString();
                    String className = typeElement.getQualifiedName().toString();
                    String annotationValue = getProvideAnnotationValue(element);
                    printNote("packageName: " + packageName);
                    printNote("className: " + className);
                    printNote("annotationValue: " + annotationValue);
                }
                return true;
            }
        }
        return false;
    }

    private String getProvideAnnotationValue(Element element) {
        try {
            Provide provide = element.getAnnotation(Provide.class);
            Class<?> clazz = provide.value();
            return clazz.getCanonicalName();
        } catch (MirroredTypeException mte) {
            DeclaredType classDeclaredType = (DeclaredType) mte.getTypeMirror();
            TypeElement classTypeElement = (TypeElement) classDeclaredType.asElement();
            return classTypeElement.getQualifiedName().toString();
        }
    }

    private void printNote(String msg, Object... args) {
        mMessager.printMessage(Diagnostic.Kind.NOTE, String.format(msg, args));
    }

    private void printError(String msg, Object... args) {
        mMessager.printMessage(Diagnostic.Kind.ERROR, String.format(msg, args));
    }
}
