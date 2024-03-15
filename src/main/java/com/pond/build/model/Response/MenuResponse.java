package com.pond.build.model.Response;

import lombok.Data;

import java.util.List;

@Data
public class MenuResponse {

    private String menuId;

    private String label;

    private String key;

    private String icon;

    private String menuParentId;

    private List<MenuResponse> children;

}
