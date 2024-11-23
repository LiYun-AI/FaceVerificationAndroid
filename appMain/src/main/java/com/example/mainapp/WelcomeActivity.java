package com.example.mainapp;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;

import com.airbnb.lottie.LottieAnimationView;
import com.tencent.bugly.crashreport.CrashReport;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.ai.face.FaceAINaviActivity;

/**
 * 演示快速集成到你的主工程，人脸识别相关放到 FaceAILIb 里面
 * 先以子module 的形式配置到你的主工程跑起来后，再根据你的业务调整
 * 1.整体拷贝faceAILib 代码到你的主程一级目录
 * 2.settings.gradle 中 include ':faceAILib'
 * 3.调整工程一级目录root级build.gradle 的
 *
 */
public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        findViewById(R.id.goFaceAILib).setOnClickListener(view -> startActivity(new Intent(WelcomeActivity.this, FaceAINaviActivity.class)));

        LottieAnimationView startFaceAILib= findViewById(R.id.goFaceAILib);
        startFaceAILib.setAnimation(R.raw.loading);
        startFaceAILib.loop(false);
        startFaceAILib.setSpeed(3f);
        startFaceAILib.playAnimation();

        //主工程调用Face AI Lib 中人脸识别功能
        startFaceAILib.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationEnd(@NonNull Animator animation) {
                startActivity(new Intent(WelcomeActivity.this, FaceAINaviActivity.class));
                WelcomeActivity.this.finish();
            }
            @Override
            public void onAnimationStart(@NonNull Animator animation) {}
            @Override
            public void onAnimationCancel(@NonNull Animator animation) {}
            @Override
            public void onAnimationRepeat(@NonNull Animator animation) {}
        });



        //Crash 收集，仅仅是Demo 需要。这不是SDK 的一部分
        CrashReport.initCrashReport(this, "36fade54d8", true);

    }
}