package org.luckyjourney.service;

import org.luckyjourney.entity.video.Video;
import org.luckyjourney.entity.user.User;
import org.luckyjourney.entity.vo.UserModel;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @description: 兴趣推送
 * @Author: Xhy
 * @CreateTime: 2023-10-26 11:54
 */
public interface InterestPushService {

    /**
     * 推入标签库
     * 传videoId,typeId
     * @param video
     */
    void pushSystemStockIn(Video video);

    /**
     * 添加分类库,用于后续随机推送分类视频
     * @param video
     */
    void pushSystemTypeStockIn(Video video);

    /**
     * 根据分类随机推送视频
     * @param typeId
     * @return
     */
    Collection<Long> listVideoIdByTypeId(Long typeId);

    /**
     * 删除标签内视频
     * @param video
     */
    void deleteSystemStockIn(Video video);

    /**
     * 用户初始化模型 -> 订阅分类
     * @param userId 用户id
     * @param labels 标签名
     */
    void initUserModel(Long userId, List<String> labels);

    /**
     * 用户模型修改概率 : 可分批次发送
     * 修改场景:
     * 1.观看浏览量到达总时长1/5  +1概率
     * 2.观看浏览量未到总时长1/5 -0.5概率
     * 3.点赞视频  +1概率
     * 4.收藏视频  +2概率
     */
    void updateUserModel(UserModel userModel);

    /**
     * 用于给用户推送视频 -> 兴趣推送
     * 推送 X 视频,包含一条和性别有关
     * @param user 传id和sex
     * @return videoIds
     */
    Collection<Long> listVideoIdByUserModel(User user);

    /**
     * 根据标签获取视频id
     * @param labelNames
     * @return
     */
    Collection<Long> listVideoIdByLabels(List<String> labelNames);

    /**
     * 删除分类库中的视频
     * @param video
     */
    void deleteSystemTypeStockIn(Video video);
}
