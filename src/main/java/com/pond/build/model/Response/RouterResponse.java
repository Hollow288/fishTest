package com.pond.build.model.Response;


import lombok.Data;

import java.util.List;

@Data
public class RouterResponse {

    private String menuId;

    private String routerParentId;

    private String path;

    private String name;

    private String component;

    private String title;

    private String icon;

    private String disableAuth;

    private String dismissTab;

    private List<RouterResponse> children;

}
