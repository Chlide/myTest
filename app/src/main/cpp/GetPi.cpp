//
// Created by 20206 on 2023/11/1.
//
#include <jni.h>
#include <string>
#include <sstream>
#include <iomanip>
#include <math.h>

double GetPi(int precision){
    double pi = 0.0;
    double sign = 1.0;
    double denominator = 1.0;

    for (int i = 0; i < precision*1e4; i++) {
        pi += sign / denominator;
        sign *= -1.0;
        denominator += 2.0;
    }

    return pi * 4.0;
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_myapplicationtest_MainActivity_stringFromGetPi(
        JNIEnv* env,
        jobject /* this */,
        jstring precision) {
    //std::string pi = std::to_string(GetPi(3));
    const char* precisionChars = env->GetStringUTFChars(precision, nullptr);
    std::string precisionStr(precisionChars);
    env->ReleaseStringUTFChars(precision, precisionChars);

    int precisionValue = std::stoi(precisionStr);
    double pi = GetPi(precisionValue);

    std::stringstream ss;
    ss << std::fixed << std::setprecision(precisionValue) << pi;
    std::string piStr = ss.str();

    return env->NewStringUTF(piStr.c_str());
}