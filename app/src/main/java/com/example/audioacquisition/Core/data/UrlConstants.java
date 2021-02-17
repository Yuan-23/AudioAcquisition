package com.example.audioacquisition.Core.data;

public interface UrlConstants {

    String MY_BASE_URL = "http://121.89.211.193:8084";
    //登录
    String Login = MY_BASE_URL + "/VoiceAssessment/user/login";
    //修改密码
    String ChangePsw = MY_BASE_URL + "/VoiceAssessment/user/updatePassword";
    //获取分类的场景
    String Kind = MY_BASE_URL + "/VoiceAssessment/user/selectSceneSort";
    //查看场景详情
    String Detail = MY_BASE_URL + "/VoiceAssessment/user/selectSceneDetail";
    //获取整个考核视频视频
    String TotalTestVideo = MY_BASE_URL + "/VoiceAssessment/user/selectWholeVideo";
    //获取分步骤视频
    String DivideVideo=MY_BASE_URL+"/VoiceAssessment/user/selectPartVideo";
    //上传录音
    String PassVideo = MY_BASE_URL + "/VoiceAssessment/user/acceptFirstVoice";
    //获取语音
    String GetVideo = MY_BASE_URL + "/VoiceAssessment/user/selectLastVoice";
    //获取录音评分
    String Score = MY_BASE_URL + "/VoiceAssessment/user/selectScore";
    //我的课程
    String Cource=MY_BASE_URL+"/VoiceAssessment/user/selectMyCourse";
    //我的训练
    String Practice=MY_BASE_URL+"/VoiceAssessment/user/selectMyTrain";
    //轮播图
    String Banner=MY_BASE_URL+"/VoiceAssessment/user/selectSlideshow";
    //情景教学获取场景
    String Teach=MY_BASE_URL+"/VoiceAssessment/user/selectSceneLearn";
    //情景教学获取对应的完整教学视频
    String TeachVideo=MY_BASE_URL+"/VoiceAssessment/user/selectSceneLearn";
    //他山之石
    String Other=MY_BASE_URL+"";

    //获取图标
    String Picture=MY_BASE_URL+"/VoiceAssessment/user/appUpload";
}
