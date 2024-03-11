package com.pond.build.controller;

import com.pond.build.model.Menu;
import com.pond.build.model.ResponseResult;
import com.pond.build.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

@RestController
public class MenuController {


    @Autowired
    private MenuService menuService;

    @GetMapping("/menu")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult getAllMenu(@RequestParam(value = "page") Integer page, @RequestParam(value = "pageSize") Integer pageSize){
        return menuService.getAllMenu(page, pageSize);
    }

    @GetMapping("/menu/only-menu")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult getOnlyMenu(@RequestParam(value = "page") Integer page, @RequestParam(value = "pageSize") Integer pageSize){
        return menuService.getOnlyMenu(page, pageSize);
    }


    @GetMapping("/menu/{menuId}/children-menu")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult menuByParentId(@PathVariable("menuId") long menuId){
        return menuService.menuByParentId(menuId);
    }

    //http://localhost:5173/fish-api/menu/1/revise-menusort
    @PutMapping("/menu/{menuId}/revise-menusort")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult reviseMenuSortByMenuId(@PathVariable("menuId") long menuId,@RequestBody Map<String,String> map){
        return menuService.reviseMenuSortByMenuId(menuId, Objects.isNull(map.get("sort"))?0 : Integer.parseInt(map.get("sort")));
    }


    @PatchMapping("/menu/{menuId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult updateMenuById(@PathVariable("menuId") long menuId, @RequestBody Menu menu){
        return menuService.updateMenuById(menuId,menu);
    }


    @PostMapping("/menu")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult createMenu(@RequestBody Menu menu){
        return menuService.createMenu(menu);
    }

    @PatchMapping("/menu/{menuId}/delete-menu")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult deleteMenuById(@PathVariable("menuId") long menuId){
        return menuService.deleteMenuById(menuId);
    }

    @GetMapping("/menu/all-menu-children")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult allMenuAneChildren(){
        return menuService.allMenuAneChildren();
    }


    @GetMapping("/menu/{roleId}/all-menuid-by-roleid")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult allMenuIdByRoleId(@PathVariable("roleId") String roleId){
        return menuService.allMenuIdByRoleId(roleId);
    }

    @PostMapping("menu/{roleId}/add-menuid-by-roleid")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseResult addMenuIdByRoleId(@PathVariable("roleId") String roleId,@RequestBody Map<String,Object> menuIds){
        return menuService.addMenuIdByRoleId(roleId,menuIds);
    }
}
