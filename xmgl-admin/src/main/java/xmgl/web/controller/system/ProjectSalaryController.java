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
import xmgl.system.domain.ProjectSalary;
import xmgl.system.service.IProjectSalaryService;
import xmgl.common.utils.poi.ExcelUtil;
import xmgl.common.core.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 *
 * @author ruoyi
 * @date 2024-01-11
 */
@RestController
@RequestMapping("/system/salary")
public class ProjectSalaryController extends BaseController
{
    @Autowired
    private IProjectSalaryService projectSalaryService;

    /**
     * 查询【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:salary:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProjectSalary projectSalary)
    {
        startPage();
        List<ProjectSalary> list = projectSalaryService.selectProjectSalaryList(projectSalary);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @PreAuthorize("@ss.hasPermi('system:salary:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProjectSalary projectSalary)
    {
        List<ProjectSalary> list = projectSalaryService.selectProjectSalaryList(projectSalary);
        ExcelUtil<ProjectSalary> util = new ExcelUtil<ProjectSalary>(ProjectSalary.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:salary:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Integer id)
    {
        return success(projectSalaryService.selectProjectSalaryById(id));
    }

    /**
     * 新增【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:salary:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProjectSalary projectSalary)
    {
        return toAjax(projectSalaryService.insertProjectSalary(projectSalary));
    }

    /**
     * 修改【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:salary:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProjectSalary projectSalary)
    {
        return toAjax(projectSalaryService.updateProjectSalary(projectSalary));
    }

    /**
     * 删除【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('system:salary:remove')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids)
    {
        return toAjax(projectSalaryService.deleteProjectSalaryByIds(ids));
    }
}
