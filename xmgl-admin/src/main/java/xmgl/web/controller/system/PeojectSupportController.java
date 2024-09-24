package xmgl.web.controller.system;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xmgl.common.annotation.Log;
import xmgl.common.core.controller.BaseController;
import xmgl.common.core.domain.AjaxResult;
import xmgl.common.enums.BusinessType;
import xmgl.system.domain.PeojectSupport;
import xmgl.system.service.IPeojectSupportService;
import xmgl.common.utils.poi.ExcelUtil;
import xmgl.common.core.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 *
 * @author ruoyi
 * @date 2024-01-11
 */
@RestController
@RequestMapping("/system/support")
public class PeojectSupportController extends BaseController
{
    @Autowired
    private IPeojectSupportService peojectSupportService;

    /**
     * 查询【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:support:list')")
    @GetMapping("/list")
    public TableDataInfo list(PeojectSupport peojectSupport)
    {
        startPage();
        List<PeojectSupport> list = peojectSupportService.selectPeojectSupportList(peojectSupport);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:support:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PeojectSupport peojectSupport)
    {
        List<PeojectSupport> list = peojectSupportService.selectPeojectSupportList(peojectSupport);
        ExcelUtil<PeojectSupport> util = new ExcelUtil<PeojectSupport>(PeojectSupport.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:support:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return success(peojectSupportService.selectPeojectSupportById(id));
    }

    /**
     * 新增【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:support:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PeojectSupport peojectSupport)
    {
        return toAjax(peojectSupportService.insertPeojectSupport(peojectSupport));
    }

    /**
     * 修改【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:support:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PeojectSupport peojectSupport)
    {
        return toAjax(peojectSupportService.updatePeojectSupport(peojectSupport));
    }

    /**
     * 删除【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:support:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(peojectSupportService.deletePeojectSupportByIds(ids));
    }
}
