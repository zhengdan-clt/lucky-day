package org.luckyjourney.service.video.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.luckyjourney.entity.video.VideoShare;
import org.luckyjourney.mapper.video.VideoShareMapper;
import org.luckyjourney.service.video.VideoShareService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xhy
 * @since 2023-10-24
 */
@Service
public class VideoShareServiceImpl extends ServiceImpl<VideoShareMapper, VideoShare> implements VideoShareService {

    @Autowired
    private VideoShareMapper videoShareMapper;

    @Override
    public boolean share(VideoShare videoShare) {
        int count  = videoShareMapper.selectCount(new LambdaQueryWrapper<VideoShare>().eq(VideoShare::getVideoId, videoShare.getVideoId()).eq(VideoShare::getUserId, videoShare.getUserId()));

        try {
            //如果该用户早就分享过了，则不用再在数据库表存数据了，防止数据量过大
            if (count == 1) return true;
            // 利用videoId和ip做为唯一索引,少一次查询
            this.save(videoShare);
        }catch (Exception e){
            // 不用删除
            return false;
        }
        return true;
    }

    @Override
    public List<Long> getShareUserId(Long videoId) {
        return this.list(new LambdaQueryWrapper<VideoShare>().eq(VideoShare::getVideoId,videoId)).stream().map(VideoShare::getUserId).collect(Collectors.toList());
    }


}
