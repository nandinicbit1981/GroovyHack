class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "/characters/hello"(view:"/_hello")
        "500"(view:'/error')
	}
}
