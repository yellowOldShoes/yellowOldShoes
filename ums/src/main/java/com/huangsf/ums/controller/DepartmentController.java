package com.huangsf.ums.controller;

import com.huangsf.ums.common.BaseResponse;
import com.huangsf.ums.common.ResultUtils;
import com.huangsf.ums.model.Department;
import com.huangsf.ums.model.Menu;
import com.huangsf.ums.service.IDepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 部门表 前端控制器
 * </p>
 *
 * @author huangsf
 * @since 2025-02-05
 */
@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Resource
    IDepartmentService departmentService;

    @GetMapping
    public BaseResponse fetchDepartments(){
        List<Department> list = departmentService.list();
        List<Department> departments = buildMenuTree(list);
        return ResultUtils.success(departments);
    }

    /**
     * 通过 parentId 递归或循环构建树
     */
    private List<Department> buildMenuTree(List<Department> menuList) {
        // 先把 list 转成 map<id, menu> 以便快速查
        Map<Long, Department> map = menuList.stream()
                .collect(Collectors.toMap(Department::getId, m -> m));

        // 准备存储顶级节点
        List<Department> rootMenus = new ArrayList<>();
        for (Department menu : menuList) {
            Long parentId = menu.getParentId();
            if (parentId == 0 || !map.containsKey(parentId)) {
                // 如果 parentId=0 或 不在 map 中 => 顶级菜单
                rootMenus.add(menu);
            } else {
                // 找到父菜单, 加到父菜单的 children 列表
                Department parent = map.get(parentId);
                if (parent.getChildren() == null) {
                    parent.setChildren(new ArrayList<>());
                }
                parent.getChildren().add(menu);
            }
        }

        // 也可递归排序子节点
        return rootMenus;
    }
}
