package com.lzx.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lzx.domain.AjaxResult;
import com.lzx.domain.entity.Article;
import com.lzx.domain.entity.ArticleItem;
import com.lzx.domain.request.ArticlePageReq;

import com.lzx.mapper.ArticleMapper;
import com.lzx.service.ArticleTagService;
import com.lzx.service.impl.ArticleItemServiceImpl;
import com.lzx.service.impl.ArticleServiceImpl;
import com.lzx.domain.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("article")

public class ArticleController {

    @Autowired
    private ArticleServiceImpl articleService;


    @Autowired
    private ArticleTagService articleTagService;

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ArticleItemServiceImpl articleItemService;

    @PostMapping("list")
    public AjaxResult list(
            @RequestBody ArticlePageReq articlePageReq) {
        System.out.println(articlePageReq);

        AjaxResult ajaxResult = AjaxResult.success();

        ajaxResult.put("data", articleItemService.getList(articlePageReq));
        return ajaxResult;
    }


    @GetMapping("get")
    public AjaxResult getArticle(@RequestParam("id") Long articleId) {
        AjaxResult ajaxResult = AjaxResult.success();
        ajaxResult.put("data", articleItemService.getArticleItemById(articleId));
        return ajaxResult;
    }


    @PostMapping("add")
    public AjaxResult addArticle(@RequestBody ArticleItem articleItem) {
        AjaxResult ajaxResult = AjaxResult.success("文章添加成功");
        articleItemService.insertArticleItem(articleItem);
        ajaxResult.put("data", articleItem);
        return ajaxResult;
    }


    @PostMapping("update")
    public AjaxResult updateArticle(@RequestBody ArticleItem articleItem) {
        AjaxResult ajaxResult = AjaxResult.success("文章更新成功");
        articleItemService.updateArticleItem(articleItem);
        ajaxResult.put("data", articleItem);
        return ajaxResult;
    }
//
//
//
//    @PostMapping("delete")
//    public AjaxResult deleteArticle(@RequestBody String json) {
//        AjaxResult ajaxResult = AjaxResult.success();
//        JSONObject jsonObject = JSON.parseObject(json);
//        Integer id = jsonObject.getObject("id", Integer.class);
//        System.out.println(id);
//        Integer flag = articleService.updateArticleState(id, -1);
//        if(flag == 1)
//        {
//            ajaxResult.put("msg", "删除成功");
//        }else{
//            ajaxResult.put("msg", "删除失败");
//        }
//        return ajaxResult;
//    }
//
//
//    @PostMapping("deleteList")
//    public AjaxResult deleteList(@RequestBody String json) {
//        AjaxResult ajaxResult = AjaxResult.success();
//        JSONObject jsonObject = JSON.parseObject(json);
//        List<Integer> ids = jsonObject.parseArray(jsonObject.getString("ids"), Integer.class);
//        Integer flag = articleService.updateArticleListState(ids, -1);
//        if(flag == ids.size())
//        {
//            ajaxResult.put("msg", "删除成功");
//        }else{
//            ajaxResult.put("msg", "删除失败");
//        }
//        return ajaxResult;
//    }
//
//
//    @PostMapping("recover")
//    public AjaxResult recoverArticle(@RequestBody String json) {
//        AjaxResult ajaxResult = AjaxResult.success();
//        JSONObject jsonObject = JSON.parseObject(json);
//        Integer id = jsonObject.getObject("id", Integer.class);
//        Integer flag = articleService.updateArticleState(id, 1);
//        if(flag == 1)
//        {
//            ajaxResult.put("msg", "还原成功");
//        }else{
//            ajaxResult.put("msg", "还原失败");
//        }
//        return ajaxResult;
//    }
//
//
//    @PostMapping("recoverList")
//    public AjaxResult recoverList(@RequestBody String json) {
//        AjaxResult ajaxResult = AjaxResult.success();
//        JSONObject jsonObject = JSON.parseObject(json);
//        List<Integer> ids = jsonObject.parseArray(jsonObject.getString("ids"), Integer.class);
//        Integer flag = articleService.updateArticleListState(ids, 1);
//        if(flag == ids.size())
//        {
//            ajaxResult.put("msg", "还原成功");
//        }else{
//            ajaxResult.put("msg", "还原失败");
//        }
//        return ajaxResult;
//    }
//
//
//
//    @PostMapping("publish")
//    public AjaxResult publishArticle(@RequestBody String json) {
//        AjaxResult ajaxResult = AjaxResult.success();
//        JSONObject jsonObject = JSON.parseObject(json);
//        Integer id = jsonObject.getObject("id", Integer.class);
//        Integer flag = articleService.updateArticleState(id, 1);
//        if(flag == 1)
//        {
//            ajaxResult.put("msg", "发布成功");
//        }else{
//            ajaxResult.put("msg", "发布失败");
//        }
//        return ajaxResult;
//    }
//
//
//    @PostMapping("publishList")
//    public AjaxResult publishList(@RequestBody String json) {
//        AjaxResult ajaxResult = AjaxResult.success();
//        JSONObject jsonObject = JSON.parseObject(json);
//        List<Integer> ids = jsonObject.parseArray(jsonObject.getString("ids"), Integer.class);
//        Integer flag = articleService.updateArticleListState(ids, 1);
//        if(flag == ids.size())
//        {
//            ajaxResult.put("msg", "发布成功");
//        }else{
//            ajaxResult.put("msg", "发布失败");
//        }
//        return ajaxResult;
//    }
//
//
//
//
//
//
//
//
//
//    @GetMapping("findAllByCategory")
//    public AjaxResult findAllByCategory(@RequestParam("curr") Integer curr, @RequestParam("limit") Integer limit,
//                                        @RequestParam(value = "tag", required = true, defaultValue = "") String tag,
//                                        @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
//                                        @RequestParam("isPublish") Boolean isPublish,
//                                        @RequestParam(value = "authorId", required = false, defaultValue = "0") Integer authorId,
//                                        @RequestParam("mode") String mode) {
//        AjaxResult ajaxResult = AjaxResult.success();
////        ajaxResult.put("list", list);
//        return ajaxResult;
//    }
//
//
//    @PostMapping("updateRelation")
//    public AjaxResult updateBook(@RequestBody ArticleReUser articleReUser) {
//        AjaxResult ajaxResult = AjaxResult.success();
//        QueryWrapper<ArticleReUser> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("article_id", articleReUser.getArticleId());
//        queryWrapper.eq("user_id", articleReUser.getUserId());
//        articleReUserMapper.update(articleReUser, queryWrapper);
//        ajaxResult.put("relation", articleReUserMapper.selectOne(queryWrapper));
//        return ajaxResult;
//    }
//
//
//    @GetMapping("getBookList")
//    public AjaxResult getBookList(
//            @RequestParam("curr") Integer curr, @RequestParam("limit") Integer limit,
//            @RequestParam(value = "userId", required = false, defaultValue = "0") Integer userId) {
//        AjaxResult ajaxResult = AjaxResult.success();
//        ajaxResult.put("total", articleService.getBookTotal(userId));
//        List<Integer> bookIds = articleService.getBookIds(curr, limit, userId);
//        List<HashMap<Object, Object>> list = new ArrayList<>();
//
//        for (Integer id : bookIds) {
//            HashMap<Object, Object> model = new HashMap<>();
//            model.put("id", id);
//            model.put("article", articleMapper.selectById(id));
//            model.put("status", articleStatusMapper.selectById(id));
//            model.put("tagList", tagService.getTags(id));
//            list.add(model);
//        }
//        ajaxResult.put("list", list);
//        return ajaxResult;
//    }


}
