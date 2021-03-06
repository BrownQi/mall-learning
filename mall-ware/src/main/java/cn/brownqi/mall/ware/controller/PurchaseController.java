package cn.brownqi.mall.ware.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import cn.brownqi.mall.ware.vo.MergeVO;
import cn.brownqi.mall.ware.vo.PurchaseDoneVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import cn.brownqi.mall.ware.entity.PurchaseEntity;
import cn.brownqi.mall.ware.service.PurchaseService;
import cn.brownqi.common.utils.PageUtils;
import cn.brownqi.common.utils.R;



/**
 * 采购信息
 *
 * @author brownqi
 * @email brownqi@foxmail.com
 * @date 2020-04-22 23:06:17
 */
@RestController
@RequestMapping("ware/purchase")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;

    @RequestMapping("/unreceive/list")
    //@RequiresPermissions("ware:purchase:list")
    public R unreceivelist(@RequestParam Map<String, Object> params){
        PageUtils page = purchaseService.queryPageUnreceivePurchase(params);

        return R.ok().put("page", page);
    }

    ///ware/purchase/unreceive/list
    ///ware/purchase/merge
    @PostMapping("/merge")
    public R merge(@RequestBody MergeVO mergeVO){
        purchaseService.mergePurchase(mergeVO);
        return R.ok();
    }

    /**
     * 领取采购单
     * @return
     */
    @PostMapping("/received")
    public R received(@RequestBody List<Long> ids){

        purchaseService.received(ids);

        return R.ok();
    }

    /**
     * 完成采购单
     * @param doneVO
     * @return
     */
    @PostMapping("/done")
    public R finish(@RequestBody PurchaseDoneVO doneVO){

        purchaseService.done(doneVO);

        return R.ok();
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = purchaseService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		PurchaseEntity purchase = purchaseService.getById(id);

        return R.ok().put("purchase", purchase);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody PurchaseEntity purchase){
		purchaseService.save(purchase);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody PurchaseEntity purchase){
		purchaseService.updateById(purchase);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		purchaseService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
