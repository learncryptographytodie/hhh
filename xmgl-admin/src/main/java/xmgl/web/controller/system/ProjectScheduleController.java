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
import xmgl.system.domain.ProjectSchedule;
import xmgl.system.service.IProjectScheduleService;
import xmgl.common.utils.poi.ExcelUtil;
import xmgl.common.core.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 *
 * @author ruoyi
 * @date 2024-01-11
 */
@RestController
@RequestMapping("/system/schedule")
public class ProjectScheduleController extends BaseController
{
    @Autowired
    private IProjectScheduleService projectScheduleService;

    /**
     * 查询【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:schedule:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProjectSchedule projectSchedule)
    {
        startPage();
        List<ProjectSchedule> list = projectScheduleService.selectProjectScheduleList(projectSchedule);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:schedule:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProjectSchedule projectSchedule)
    {
        List<ProjectSchedule> list = projectScheduleService.selectProjectScheduleList(projectSchedule);
        ExcelUtil<ProjectSchedule> util = new ExcelUtil<ProjectSchedule>(ProjectSchedule.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:schedule:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return success(projectScheduleService.selectProjectScheduleById(id));
    }

    /**
     * 新增【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:schedule:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProjectSchedule projectSchedule)
    {
        return toAjax(projectScheduleService.insertProjectSchedule(projectSchedule));
    }

    /**
     * 修改【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:schedule:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProjectSchedule projectSchedule)
    {
        return toAjax(projectScheduleService.updateProjectSchedule(projectSchedule));
    }

    /**
     * 删除【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:schedule:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(projectScheduleService.deleteProjectScheduleByIds(ids));
    }
}
