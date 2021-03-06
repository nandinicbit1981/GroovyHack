
<html>
<head>
    <title>Crush the Grails</title>
</head>

<script type="text/javascript" src="${resource()}/js/jquery.min.js"></script>
<script type="text/javascript" src="${resource()}/js/main.js"></script>
<script type="text/javascript" src="${resource()}/js/bootstrap.js"></script>
<link rel="stylesheet" type="text/css" href="${resource()}/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="${resource()}/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${resource()}/css/bootstrap-theme.css">
<link rel="stylesheet" type="text/css" href="${resource()}/css/style.css">

<body class="background-dd">
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand text-white" href="${resource()}" >
                <span class="text-white h4">Dungeons & Dragons</span>
            </a>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li>
                    <a href="${resource()}/characters/" >
                        <span class="text-white">all</span>
                    </a>

                </li>
                <li>
                    <a href="${resource()}/characters/createForm">
                        <span class="text-white">create</span>
                    </a>
                    %{--<a href="/characters/create"><span class="text-white">Create</span></a>--}%
                </li>
            </ul>
        </div>
    </div>
</nav>
<div class="row center">
    <div class="col-sm-15 background-white" style="padding: 50px;">
        <div class="page-header">

            <h1>Dungeons & Dragons</h1>

        </div>
        <div>
            <label> Character</label>
            <form class="form-horizontal">
                <div class="form-group">
                    <label for="name">Name:</label>
                    <input type="name" class="form-control" id="name" value="${characters.name}">
                </div>
                <div class="form-group">
                    <label>Class:</label>
                    <div>
                        <select class="selectpicker" id="classSelect" value="${characters.className}">

                            <optgroup label="select">
                                <option value="Fighter">Fighter</option>
                                <option value="Cleric">Cleric</option>
                                <option value="Wizard">Wizard</option>
                                <option value="Thief">Thief</option>
                            </optgroup>

                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <label>Race:</label>
                    <div>
                        <select class="selectpicker" id="raceSelect" value="${characters.race}">
                            <optgroup label="select">
                                <option>Human</option>
                                <option>Dwarf</option>
                                <option>Elf</option>
                                <option>Half-elf</option>
                            </optgroup>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <label>Strength:</label>
                    <div class="row">

                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="strength_stat"  value="${characters.strength_stat}">
                        </div>
                        <div class="col-sm-3">
                            <input type="text" class="form-control"  disabled="disabled" id="strength_mod" value="${characters.strength_mod}">
                        </div>
                        <div class="col-sm-3">
                            <button id="strength_roll" class="form-control btn btn-info">roll</button>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label>Dexterity:</label>
                    <div class="row">

                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="dex_stat" value="${characters.dex_stat}">
                        </div>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" disabled="disabled" id="dex_mod" value="${characters.dex_mod}">
                        </div>
                        <div class="col-sm-3">
                            <button id="dex_roll" class="form-control btn btn-info">roll</button>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label>Constitution:</label>
                    <div class="row">
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="con_stat" value="${characters.con_stat}">
                        </div>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" disabled="disabled" id="con_mod" value="${characters.con_mod}">
                        </div>
                        <div class="col-sm-3">
                            <button id="con_roll" class="form-control btn btn-info">roll</button>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label>Intelligence:</label>
                    <div class="row">

                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="intl_stat" value="${characters.intl_stat}">
                        </div>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" disabled="disabled" id="intl_mod" value="${characters.intl_mod}">
                        </div>
                        <div class="col-sm-3">
                            <button id="intl_roll" class="form-control btn btn-info">roll</button>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label>Wisdom:</label>
                    <div class="row">

                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="wsdm_stat" value="${characters.wsdm_stat}">
                        </div>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" disabled="disabled" id="wsdm_mod" value="${characters.wsdm_mod}">
                        </div>
                        <div class="col-sm-3">
                            <button id="wsdm_roll" class="form-control btn btn-info">roll</button>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label>Charisma:</label>
                    <div class="row">
                        <div class="col-sm-3">
                            <input type="text" class="form-control" id="charisma_stat" value="${characters.chr_stat}">
                        </div>
                        <div class="col-sm-3">
                            <input type="text" class="form-control" disabled="disabled" id="charisma_mod" value="${characters.chr_mod}">
                        </div>
                        <div class="col-sm-3">
                            <button id="charisma_roll" class="form-control btn btn-info">roll</button>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label>AC:</label>
                    <div class="row">
                        <div class="col-sm-5">
                            <input type="text" disabled="disabled" class="form-control" id="ac" value="${characters.ac}">
                        </div>

                    </div>
                </div>
                <button type="button" class="btn btn-default" id="editSubmitBtn" data-id="${characters.id}">Submit</button>
                <button type="button"  id="deleteCharacter" class="btn btn-default" data-id="${characters.id}">delete</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>

