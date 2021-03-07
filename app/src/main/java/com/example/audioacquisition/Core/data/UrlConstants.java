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
    //    //获取分步骤视频
//    String DivideVideo = MY_BASE_URL + "/VoiceAssessment/user/selectPartVideo";
    //上传录音
    String PassVideo = MY_BASE_URL + "/VoiceAssessment/user/acceptFirstVoice";
    //获取语音
    String GetVideo = MY_BASE_URL + "/VoiceAssessment/user/selectLastVoice";
    //获取录音评分
    String Score = MY_BASE_URL + "/VoiceAssessment/user/selectScore";
    //我的课程
    String Cource = MY_BASE_URL + "/VoiceAssessment/user/selectMyCourse";
    //我的训练
    String Practice = MY_BASE_URL + "/VoiceAssessment/user/selectMyTrain";
    //我的考试
    String MyTest = MY_BASE_URL + "/VoiceAssessment/user/selectExam";
    //我的考试详情
    String TestDetail = MY_BASE_URL + "/VoiceAssessment/user/selectExamRank";
    //轮播图
    String Banner = MY_BASE_URL + "/VoiceAssessment/user/selectSlideshow";
    //情景教学获取场景名称
    String Teach = MY_BASE_URL + "/VoiceAssessment/user/selectSceneLearn";
    //情景教学获取对应的完整教学视频
    String TeachVideo = MY_BASE_URL + "/VoiceAssessment/user/selectSceneDetailLearn";
    //查看他山之石
    String Other = MY_BASE_URL + "/VoiceAssessment/user/otherStone";

    //获取图标
    String Picture = MY_BASE_URL + "/VoiceAssessment/user/appUpload";

    //修改个人信息
    String Information = MY_BASE_URL + "/VoiceAssessment/user/updateNickname";
    //修改个人头像
    String Portrait = MY_BASE_URL + "/VoiceAssessment/user/updateHeadPortrait";
    //查询部门
    String Department = MY_BASE_URL + "/VoiceAssessment/user/selectDepartment";
    //修改部门
    String ChangeDepart = MY_BASE_URL + "/VoiceAssessment/user/updateDepart";
    //考试中心列表
    String TestList = MY_BASE_URL + "/VoiceAssessment/user/selectMyExam";
    //查看一线传真
    String FaxList = MY_BASE_URL + "/VoiceAssessment/user/selectVideoToTeacher";
    //上传一线传真
    String ADDFax = MY_BASE_URL + "/VoiceAssessment/user/insertVideoToTeacher";
    //一线传真详情
    String FaxDetail = MY_BASE_URL + "/VoiceAssessment/user/selectVideoToTeacherDetail";
    //意见反馈
    String Suggestion = MY_BASE_URL + "/VoiceAssessment/user/feedback";

    //我的学习推荐
    String StudyNew = MY_BASE_URL + "/VoiceAssessment/user/selectMyLearnRecommend";
    //我的学习推荐详情
    String StudyNewDetail = MY_BASE_URL + "/VoiceAssessment/user/selectMyLearnRecommendDetail";

    //他山之石详情
    String OtherDetail = MY_BASE_URL + "/VoiceAssessment/user/otherStoneDetail";

    //政策法规
    String Policy = MY_BASE_URL + "/VoiceAssessment/user/selectPolicyRule";

    //政策法规详情
    String PolicyDetail = MY_BASE_URL + "/VoiceAssessment/user/selectPolicyRuleDetail";
}
