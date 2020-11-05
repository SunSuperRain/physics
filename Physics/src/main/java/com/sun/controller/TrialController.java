package com.sun.controller;

import com.sun.corba.se.spi.orb.ParserImplBase;
import com.sun.entity.Teacher;
import com.sun.entity.Trial;
import com.sun.service.TrialService;
import com.sun.service.VisionService;
import com.sun.utils.PageUtil;
import com.sun.utils.PageUtils;
import com.sun.utils.ParseInt;
import com.sun.utils.RestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

/**
 * @author 超雨
 * @create 2020--10--04--20:08
 */
@Controller
@RequestMapping("/trial")
public class TrialController {
    //controller层调用service方法
    //多态
    @Autowired
    @Qualifier("TrialServiceImpl")
    private TrialService trialService;

    @Autowired
    @Qualifier("VisionServiceImpl")
    private VisionService visionService;

    //获取所有实验内容
    @RequestMapping("/allList")
    public String getAllTrial(Model model,String pageNo,String trialName){
        //查询所有实验信息   分页   模糊查询
        model.addAttribute("pageList",trialService.queryByPaging(ParseInt.getInteger(pageNo),trialName));

        //回显查询信息
        model.addAttribute("trialName",trialName);

        //获取所有实验个数
        return "trial/more";
    }

    //获取部分的实验内容
    @RequestMapping("/list")
    public String list(Model model){
        //获取前四个实验信息
        model.addAttribute("firstList",trialService.getFirstTrial());
        //获取后四个实验信息
        model.addAttribute("lastList",trialService.getLastTrial());

        //获取前四个视频信息
        model.addAttribute("firstVision",visionService.getFirstVision());
        //获取后四个视频信息
        model.addAttribute("lastVision",visionService.getLastVision());

        //返回实验首页
        return "trial/index";
    }

    //根据编号获取实验信息
    @RequestMapping("/getByNo/{trialNo}")
    public String getByNo(@PathVariable("trialNo") Integer trialNo,Model model){
        model.addAttribute("trial",trialService.getByNo(trialNo));
        //转发到实验内容介绍页面
        return "trial/introduction";
    }

    //根据编号开始实验
    @RequestMapping("/startTrial/{trialNo}")
    public String startTrial(@PathVariable("trialNo") Integer trialNo){
        //记录学生点击实验的次数  便于后期统计实验次数
        //将记录自增加一  并存储到数据库中
        Integer counts = trialService.getByNo(trialNo).getCounts();
        trialService.addCountByNo(trialNo,++counts);
        return "Physics/index";
    }

    //用户进行模糊查询
    @RequestMapping("/queryByName")
    public String queryByName(Model model,String trialName){
        model.addAttribute("list",trialService.queryByName(trialName));
        model.addAttribute("trialName",trialName);
        return "/trial/more";
    }

    //利用分页和模糊查询   对数据进行管理
    @RequestMapping("/paging/{condition}")
    public String paging(@PathVariable("condition") String condition){
        System.out.println(condition);
        return "";
    }

    @RequestMapping("toList")
    public String  toList(){
        return "trial/list";
    }

    //获取所有实验的信息
    @RequestMapping("/trialList")
    @ResponseBody
    public RestResponse List(Integer page, Integer limit, @Nullable Trial trial){
        List<Trial> trialList = trialService.getAllTrials(trial);
        if(trialList.size() != 0){
            HashMap hashMap = PageUtil.PageByList(trialList,page,limit,trialList.size());
            return  RestResponse.ok(hashMap);
        }
        return RestResponse.fail(200,"请求数据异常");
    }

    //跳转到编辑页面   附带会显示数据
    @RequestMapping("/toEdit")
    public String toEdit(Integer trialNo,Model model){
        //查询实验信息
        model.addAttribute("trial",trialService.getByNo(trialNo));
        return "trial/edit";
    }

    @RequestMapping("/delete")
    //提供删除实验的方法
    public void deleteTrial(Integer trialNo){
        trialService.deleteTrial(trialNo);
    }

    //跳转到增加页面
    @RequestMapping("toAdd")
    public String toAdd(){
        return "trial/add";
    }

    //提供添加一个实验的方法
    @RequestMapping("/addTrial")
    public String addTrial(Trial trial){
        //添加实验服务器地址信息  到数据库中
        trial.setImage("服务器地址");
        trial.setFigure("服务器地址");
        trial.setTrialLink("服务器地址");
        trialService.addTrial(trial);
        return "trial/list";
    }

    //批量删除实验信息
    @RequestMapping("/batchDelete")
    @ResponseBody
    public void batchDelete(String arrayStr){
        trialService.batchDelete(arrayStr);
    }
}