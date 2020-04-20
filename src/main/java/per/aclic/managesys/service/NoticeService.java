package per.aclic.managesys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import per.aclic.managesys.dao.NoticeMapper;
import per.aclic.managesys.model.Notice;
import per.aclic.managesys.model.NoticeExample;

import java.util.List;

@Service
public class NoticeService {

    @Autowired
    NoticeMapper noticeMapper;

    public int addNotice(Notice notice){
        return noticeMapper.insert(notice);
    }

    public List<Notice> findAll(){
        return noticeMapper.selectByExample(new NoticeExample());
    }

    public int delNotice(String id){
        return noticeMapper.deleteByPrimaryKey(id);
    }

//    主界面的最新通知
    public List<Notice> findRecentNotice(int limit){
        return noticeMapper.selectByLimit(limit);
    }
}
