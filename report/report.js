$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:testcases/string_ListOfValue.feature");
formatter.feature({
  "name": "DS STRING LIST OF VALUES",
  "description": "",
  "keyword": "Feature"
});
formatter.scenarioOutline({
  "name": "Log into Author",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@Author"
    }
  ]
});
formatter.step({
  "name": "User navigate to Author login page",
  "keyword": "Given "
});
formatter.step({
  "name": "User enters \"\u003cusername\u003e\" and \"\u003cpassword\u003e\" in Author login page",
  "keyword": "And "
});
formatter.step({
  "name": "Verify User has Logged in successfully in Author Url",
  "keyword": "And "
});
formatter.examples({
  "name": "",
  "description": "",
  "keyword": "Examples",
  "rows": [
    {
      "cells": [
        "username",
        "password"
      ]
    },
    {
      "cells": [
        "deepak@appveen.com",
        "123123123"
      ]
    }
  ]
});
formatter.scenario({
  "name": "Log into Author",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@Author"
    }
  ]
});
formatter.before({
  "status": "passed"
});
formatter.before({
  "error_message": "io.github.bonigarcia.wdm.WebDriverManagerException: java.net.UnknownHostException: npm.taobao.org\r\n\tat io.github.bonigarcia.wdm.WebDriverManager.handleException(WebDriverManager.java:774)\r\n\tat io.github.bonigarcia.wdm.WebDriverManager.manage(WebDriverManager.java:548)\r\n\tat io.github.bonigarcia.wdm.WebDriverManager.handleException(WebDriverManager.java:771)\r\n\tat io.github.bonigarcia.wdm.WebDriverManager.manage(WebDriverManager.java:548)\r\n\tat io.github.bonigarcia.wdm.WebDriverManager.handleException(WebDriverManager.java:764)\r\n\tat io.github.bonigarcia.wdm.WebDriverManager.manage(WebDriverManager.java:548)\r\n\tat io.github.bonigarcia.wdm.WebDriverManager.setup(WebDriverManager.java:258)\r\n\tat base.BaseClass.start(BaseClass.java:75)\r\n\tat stepdefinitions.LoginToAuthorUrl.setUp(LoginToAuthorUrl.java:42)\r\nCaused by: java.net.UnknownHostException: npm.taobao.org\r\n\tat java.base/java.net.InetAddress$CachedAddresses.get(InetAddress.java:797)\r\n\tat java.base/java.net.InetAddress.getAllByName0(InetAddress.java:1505)\r\n\tat java.base/java.net.InetAddress.getAllByName(InetAddress.java:1364)\r\n\tat java.base/java.net.InetAddress.getAllByName(InetAddress.java:1298)\r\n\tat org.apache.http.impl.conn.SystemDefaultDnsResolver.resolve(SystemDefaultDnsResolver.java:45)\r\n\tat org.apache.http.impl.conn.DefaultHttpClientConnectionOperator.connect(DefaultHttpClientConnectionOperator.java:112)\r\n\tat org.apache.http.impl.conn.PoolingHttpClientConnectionManager.connect(PoolingHttpClientConnectionManager.java:373)\r\n\tat org.apache.http.impl.execchain.MainClientExec.establishRoute(MainClientExec.java:394)\r\n\tat org.apache.http.impl.execchain.MainClientExec.execute(MainClientExec.java:237)\r\n\tat org.apache.http.impl.execchain.ProtocolExec.execute(ProtocolExec.java:185)\r\n\tat org.apache.http.impl.execchain.RetryExec.execute(RetryExec.java:89)\r\n\tat org.apache.http.impl.execchain.RedirectExec.execute(RedirectExec.java:110)\r\n\tat org.apache.http.impl.client.InternalHttpClient.doExecute(InternalHttpClient.java:185)\r\n\tat org.apache.http.impl.client.CloseableHttpClient.execute(CloseableHttpClient.java:83)\r\n\tat org.apache.http.impl.client.CloseableHttpClient.execute(CloseableHttpClient.java:108)\r\n\tat io.github.bonigarcia.wdm.HttpClient.execute(HttpClient.java:169)\r\n\tat io.github.bonigarcia.wdm.WebDriverManager.getDriversFromMirror(WebDriverManager.java:1041)\r\n\tat io.github.bonigarcia.wdm.ChromeDriverManager.getDrivers(ChromeDriverManager.java:82)\r\n\tat io.github.bonigarcia.wdm.WebDriverManager.filterCandidateUrls(WebDriverManager.java:790)\r\n\tat io.github.bonigarcia.wdm.WebDriverManager.downloadAndExport(WebDriverManager.java:566)\r\n\tat io.github.bonigarcia.wdm.WebDriverManager.manage(WebDriverManager.java:545)\r\n\tat io.github.bonigarcia.wdm.WebDriverManager.handleException(WebDriverManager.java:771)\r\n\tat io.github.bonigarcia.wdm.WebDriverManager.manage(WebDriverManager.java:548)\r\n\tat io.github.bonigarcia.wdm.WebDriverManager.handleException(WebDriverManager.java:764)\r\n\tat io.github.bonigarcia.wdm.WebDriverManager.manage(WebDriverManager.java:548)\r\n\tat io.github.bonigarcia.wdm.WebDriverManager.setup(WebDriverManager.java:258)\r\n\tat base.BaseClass.start(BaseClass.java:75)\r\n\tat stepdefinitions.LoginToAuthorUrl.setUp(LoginToAuthorUrl.java:42)\r\n\tat java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\r\n\tat java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\r\n\tat java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\r\n\tat java.base/java.lang.reflect.Method.invoke(Method.java:566)\r\n\tat io.cucumber.java.Invoker.invoke(Invoker.java:27)\r\n\tat io.cucumber.java.JavaHookDefinition.execute(JavaHookDefinition.java:61)\r\n\tat io.cucumber.core.runner.CoreHookDefinition.execute(CoreHookDefinition.java:31)\r\n\tat io.cucumber.core.runner.HookDefinitionMatch.runStep(HookDefinitionMatch.java:20)\r\n\tat io.cucumber.core.runner.TestStep.executeStep(TestStep.java:64)\r\n\tat io.cucumber.core.runner.TestStep.run(TestStep.java:49)\r\n\tat io.cucumber.core.runner.TestCase.run(TestCase.java:47)\r\n\tat io.cucumber.core.runner.Runner.runPickle(Runner.java:67)\r\n\tat io.cucumber.core.runtime.Runtime.lambda$run$2(Runtime.java:100)\r\n\tat java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:515)\r\n\tat java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264)\r\n\tat io.cucumber.core.runtime.Runtime$SameThreadExecutorService.execute(Runtime.java:243)\r\n\tat java.base/java.util.concurrent.AbstractExecutorService.submit(AbstractExecutorService.java:118)\r\n\tat io.cucumber.core.runtime.Runtime.lambda$run$3(Runtime.java:100)\r\n\tat java.base/java.util.stream.ReferencePipeline$3$1.accept(ReferencePipeline.java:195)\r\n\tat java.base/java.util.stream.SliceOps$1$1.accept(SliceOps.java:199)\r\n\tat java.base/java.util.ArrayList$ArrayListSpliterator.tryAdvance(ArrayList.java:1632)\r\n\tat java.base/java.util.stream.ReferencePipeline.forEachWithCancel(ReferencePipeline.java:127)\r\n\tat java.base/java.util.stream.AbstractPipeline.copyIntoWithCancel(AbstractPipeline.java:502)\r\n\tat java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:488)\r\n\tat java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)\r\n\tat java.base/java.util.stream.ReduceOps$ReduceOp.evaluateSequential(ReduceOps.java:913)\r\n\tat java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)\r\n\tat java.base/java.util.stream.ReferencePipeline.collect(ReferencePipeline.java:578)\r\n\tat io.cucumber.core.runtime.Runtime.run(Runtime.java:101)\r\n\tat io.cucumber.core.cli.Main.run(Main.java:75)\r\n\tat io.cucumber.core.cli.Main.main(Main.java:31)\r\n\tat testrunner.Runner.main(Runner.java:33)\r\n",
  "status": "failed"
});
formatter.before({
  "status": "passed"
});
formatter.before({
  "status": "passed"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "User navigate to Author login page",
  "keyword": "Given "
});
formatter.match({
  "location": "stepdefinitions.LoginToAuthorUrl.user_Navigate_to_LogIn_Page()"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "User enters \"deepak@appveen.com\" and \"123123123\" in Author login page",
  "keyword": "And "
});
formatter.match({
  "location": "stepdefinitions.LoginToAuthorUrl.user_enters_UserName_and_Password(java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "Verify User has Logged in successfully in Author Url",
  "keyword": "And "
});
formatter.match({
  "location": "stepdefinitions.LoginToAuthorUrl.message_displayed_Login_Successfully()"
});
formatter.result({
  "status": "skipped"
});
formatter.scenario({
  "name": "Delete data service",
  "description": "",
  "keyword": "Scenario"
});
formatter.before({
  "status": "passed"
});
formatter.before({
  "status": "passed"
});
formatter.before({
  "status": "passed"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "Data service \"string_ListOfValue\" exists",
  "keyword": "Given "
});
formatter.match({
  "location": "stepdefinitions.LoginToAuthorUrl.data_service_exists(java.lang.String)"
});
formatter.result({
  "error_message": "java.lang.NullPointerException\r\n\tat pageModules.LoginPage.verifyDataServiceExist(LoginPage.java:91)\r\n\tat stepdefinitions.LoginToAuthorUrl.data_service_exists(LoginToAuthorUrl.java:71)\r\n\tat ✽.Data service \"string_ListOfValue\" exists(file:///C:/Users/DELL/eclipse-workspace/ds-dev-ui-automation-framework/testcases/string_ListOfValue.feature:15)\r\n",
  "status": "failed"
});
formatter.step({
  "name": "Remove the data service",
  "keyword": "Then "
});
formatter.match({
  "location": "stepdefinitions.LoginToAuthorUrl.remove_the_data_service()"
});
formatter.result({
  "status": "skipped"
});
formatter.scenario({
  "name": "Create data service",
  "description": "",
  "keyword": "Scenario"
});
formatter.before({
  "status": "passed"
});
formatter.before({
  "status": "passed"
});
formatter.before({
  "status": "passed"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "Data service \"string_ListOfValue\" does not exist",
  "keyword": "Given "
});
formatter.match({
  "location": "stepdefinitions.LoginToAuthorUrl.data_service_does_not_exist(java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Create new data service \"string_ListOfValue\"",
  "keyword": "Then "
});
formatter.match({
  "location": "stepdefinitions.LoginToAuthorUrl.create_new_Data_Service(java.lang.String)"
});
formatter.result({
  "error_message": "java.lang.NullPointerException\r\n\tat pageModules.LoginPage.createNewDataServices(LoginPage.java:120)\r\n\tat stepdefinitions.LoginToAuthorUrl.create_new_Data_Service(LoginToAuthorUrl.java:110)\r\n\tat ✽.Create new data service \"string_ListOfValue\"(file:///C:/Users/DELL/eclipse-workspace/ds-dev-ui-automation-framework/testcases/string_ListOfValue.feature:20)\r\n",
  "status": "failed"
});
formatter.scenarioOutline({
  "name": "Assign to Appcenter Group",
  "description": "",
  "keyword": "Scenario Outline"
});
formatter.step({
  "name": "Group \"String-ListOfValue\" does not exist",
  "keyword": "Then "
});
formatter.step({
  "name": "Create new group \"String-ListOfValue\"",
  "keyword": "Then "
});
formatter.step({
  "name": "Assign appcenter permissions for \"string_ListOfValue\" dataservice to \"\u003cuser\u003e\"",
  "keyword": "And "
});
formatter.examples({
  "name": "",
  "description": "",
  "keyword": "Examples",
  "rows": [
    {
      "cells": [
        "user"
      ]
    },
    {
      "cells": [
        "maker@appveen.com"
      ]
    }
  ]
});
formatter.scenario({
  "name": "Assign to Appcenter Group",
  "description": "",
  "keyword": "Scenario Outline"
});
formatter.before({
  "status": "passed"
});
formatter.before({
  "status": "passed"
});
formatter.before({
  "status": "passed"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "Group \"String-ListOfValue\" does not exist",
  "keyword": "Then "
});
formatter.match({
  "location": "stepdefinitions.DesignTestCases.group_does_not_exist(java.lang.String)"
});
formatter.result({
  "error_message": "java.lang.NullPointerException\r\n\tat org.openqa.selenium.support.pagefactory.DefaultElementLocator.findElement(DefaultElementLocator.java:69)\r\n\tat org.openqa.selenium.support.pagefactory.internal.LocatingElementHandler.invoke(LocatingElementHandler.java:38)\r\n\tat com.sun.proxy.$Proxy12.click(Unknown Source)\r\n\tat pageModules.Module_DesignTestCases.groupexist(Module_DesignTestCases.java:95)\r\n\tat stepdefinitions.DesignTestCases.group_does_not_exist(DesignTestCases.java:58)\r\n\tat ✽.Group \"String-ListOfValue\" does not exist(file:///C:/Users/DELL/eclipse-workspace/ds-dev-ui-automation-framework/testcases/string_ListOfValue.feature:24)\r\n",
  "status": "failed"
});
formatter.step({
  "name": "Create new group \"String-ListOfValue\"",
  "keyword": "Then "
});
formatter.match({
  "location": "stepdefinitions.DesignTestCases.create_new_group(java.lang.String)"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "Assign appcenter permissions for \"string_ListOfValue\" dataservice to \"maker@appveen.com\"",
  "keyword": "And "
});
formatter.match({
  "location": "stepdefinitions.DesignTestCases.assign_appcenter_permissions_for_strings_text_dataservice(java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "skipped"
});
formatter.scenario({
  "name": "Log out of Author",
  "description": "",
  "keyword": "Scenario"
});
formatter.before({
  "status": "passed"
});
formatter.before({
  "status": "passed"
});
formatter.before({
  "status": "passed"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "User logged into Author",
  "keyword": "Given "
});
formatter.match({
  "location": "stepdefinitions.LoginToAuthorUrl.user_logged_into_Author()"
});
formatter.result({
  "error_message": "java.lang.NullPointerException\r\n\tat org.openqa.selenium.support.pagefactory.DefaultElementLocator.findElement(DefaultElementLocator.java:69)\r\n\tat org.openqa.selenium.support.pagefactory.internal.LocatingElementHandler.invoke(LocatingElementHandler.java:38)\r\n\tat com.sun.proxy.$Proxy12.click(Unknown Source)\r\n\tat pageModules.LoginPage.logOutFromAuthor(LoginPage.java:1241)\r\n\tat stepdefinitions.LoginToAuthorUrl.user_logged_into_Author(LoginToAuthorUrl.java:160)\r\n\tat ✽.User logged into Author(file:///C:/Users/DELL/eclipse-workspace/ds-dev-ui-automation-framework/testcases/string_ListOfValue.feature:34)\r\n",
  "status": "failed"
});
formatter.step({
  "name": "User logs out of Author",
  "keyword": "Then "
});
formatter.match({
  "location": "stepdefinitions.LoginToAuthorUrl.user_logs_out_of_Author()"
});
formatter.result({
  "status": "skipped"
});
formatter.scenarioOutline({
  "name": "Log into AppCenter",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@AppCenter"
    }
  ]
});
formatter.step({
  "name": "User navigate to AppCenter login page",
  "keyword": "Given "
});
formatter.step({
  "name": "User enters \"\u003cusername\u003e\" and \"\u003cpassword\u003e\" in AppCenter login page",
  "keyword": "And "
});
formatter.step({
  "name": "Verify User has Logged in Successfully",
  "keyword": "And "
});
formatter.examples({
  "name": "",
  "description": "",
  "keyword": "Examples",
  "rows": [
    {
      "cells": [
        "username",
        "password"
      ]
    },
    {
      "cells": [
        "maker@appveen.com",
        "123123123"
      ]
    }
  ]
});
formatter.scenario({
  "name": "Log into AppCenter",
  "description": "",
  "keyword": "Scenario Outline",
  "tags": [
    {
      "name": "@AppCenter"
    }
  ]
});
formatter.before({
  "status": "passed"
});
formatter.before({
  "status": "passed"
});
formatter.before({
  "status": "passed"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "User navigate to AppCenter login page",
  "keyword": "Given "
});
formatter.match({
  "location": "stepdefinitions.AppcenterWorkflow.user_navigate_to_AppCenter_login_page()"
});
formatter.result({
  "error_message": "java.lang.NullPointerException\r\n\tat helperMethods.SwitchWindow.openNewTab(SwitchWindow.java:45)\r\n\tat pageModules.WorkflowsInAppcenterPage.userNavigateToAppCenter(WorkflowsInAppcenterPage.java:123)\r\n\tat stepdefinitions.AppcenterWorkflow.user_navigate_to_AppCenter_login_page(AppcenterWorkflow.java:53)\r\n\tat ✽.User navigate to AppCenter login page(file:///C:/Users/DELL/eclipse-workspace/ds-dev-ui-automation-framework/testcases/string_ListOfValue.feature:40)\r\n",
  "status": "failed"
});
formatter.step({
  "name": "User enters \"maker@appveen.com\" and \"123123123\" in AppCenter login page",
  "keyword": "And "
});
formatter.match({
  "location": "stepdefinitions.LoginToAppCenter.user_enters_username_and_password_in_AppCenter_Url(java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "Verify User has Logged in Successfully",
  "keyword": "And "
});
formatter.match({
  "location": "stepdefinitions.LoginToAppCenter.verify_User_Login_Successfully_in_Appcenter_Url()"
});
formatter.result({
  "status": "skipped"
});
formatter.scenario({
  "name": "Add data to data service",
  "description": "",
  "keyword": "Scenario"
});
formatter.before({
  "status": "passed"
});
formatter.before({
  "status": "passed"
});
formatter.before({
  "status": "passed"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "Data service \"string_ListOfValue\"",
  "keyword": "Given "
});
formatter.match({
  "location": "stepdefinitions.LoginToAppCenter.data_service(java.lang.String)"
});
formatter.result({
  "error_message": "java.lang.NullPointerException\r\n\tat pageModules.LoginAppCenter.dataService(LoginAppCenter.java:81)\r\n\tat stepdefinitions.LoginToAppCenter.data_service(LoginToAppCenter.java:56)\r\n\tat ✽.Data service \"string_ListOfValue\"(file:///C:/Users/DELL/eclipse-workspace/ds-dev-ui-automation-framework/testcases/string_ListOfValue.feature:50)\r\n",
  "status": "failed"
});
formatter.step({
  "name": "Add data to the data service",
  "keyword": "Then "
});
formatter.match({
  "location": "stepdefinitions.LoginToAppCenter.add_data_to_the_data_service()"
});
formatter.result({
  "status": "skipped"
});
formatter.scenarioOutline({
  "name": "Add record to data service",
  "description": "",
  "keyword": "Scenario Outline"
});
formatter.step({
  "name": "Data service \"string_ListOfValue\"",
  "keyword": "Given "
});
formatter.step({
  "name": "Add record \"\u003cdata\u003e\" to the data service",
  "keyword": "Then "
});
formatter.step({
  "name": "Expect error \"DS-STRING-LIST-OF-VALUES-1002 error\" on label \"DS-STRING-LIST-OF-VALUES-1002 label\"",
  "keyword": "And "
});
formatter.step({
  "name": "Save button is disabled",
  "keyword": "And "
});
formatter.examples({
  "name": "",
  "description": "",
  "keyword": "Examples",
  "rows": [
    {
      "cells": [
        "data"
      ]
    },
    {
      "cells": [
        "{\"_id\":\"STR1002\", \"dsStringListOfValues1001\":\"LIST 1\",\"dsStringListOfValues1002\":\"\", \"dsStringListOfValues1014\":\"\",\"dsStringListOfValues1015\":\"\",\"dsStringListOfValues1018\":\"\"}"
      ]
    }
  ]
});
formatter.scenario({
  "name": "Add record to data service",
  "description": "",
  "keyword": "Scenario Outline"
});
formatter.before({
  "status": "passed"
});
formatter.before({
  "status": "passed"
});
formatter.before({
  "status": "passed"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "Data service \"string_ListOfValue\"",
  "keyword": "Given "
});
formatter.match({
  "location": "stepdefinitions.LoginToAppCenter.data_service(java.lang.String)"
});
formatter.result({
  "error_message": "java.lang.NullPointerException\r\n\tat pageModules.LoginAppCenter.dataService(LoginAppCenter.java:81)\r\n\tat stepdefinitions.LoginToAppCenter.data_service(LoginToAppCenter.java:56)\r\n\tat ✽.Data service \"string_ListOfValue\"(file:///C:/Users/DELL/eclipse-workspace/ds-dev-ui-automation-framework/testcases/string_ListOfValue.feature:55)\r\n",
  "status": "failed"
});
formatter.step({
  "name": "Add record \"{\"_id\":\"STR1002\", \"dsStringListOfValues1001\":\"LIST 1\",\"dsStringListOfValues1002\":\"\", \"dsStringListOfValues1014\":\"\",\"dsStringListOfValues1015\":\"\",\"dsStringListOfValues1018\":\"\"}\" to the data service",
  "keyword": "Then "
});
formatter.match({
  "location": "stepdefinitions.DesignTestCases.add_record(java.lang.String)"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "Expect error \"DS-STRING-LIST-OF-VALUES-1002 error\" on label \"DS-STRING-LIST-OF-VALUES-1002 label\"",
  "keyword": "And "
});
formatter.match({
  "location": "stepdefinitions.DesignTestCases.expect_error_on_label(java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "Save button is disabled",
  "keyword": "And "
});
formatter.match({
  "location": "stepdefinitions.DesignTestCases.save_button_is_disabled()"
});
formatter.result({
  "status": "skipped"
});
formatter.scenarioOutline({
  "name": "Add record to data service",
  "description": "",
  "keyword": "Scenario Outline"
});
formatter.step({
  "name": "Data service \"string_ListOfValue\"",
  "keyword": "Given "
});
formatter.step({
  "name": "Add record \"\u003cdata\u003e\" to the data service",
  "keyword": "Then "
});
formatter.step({
  "name": "Expect error \"ID STR1001 already exists\" on save",
  "keyword": "And "
});
formatter.examples({
  "name": "",
  "description": "",
  "keyword": "Examples",
  "rows": [
    {
      "cells": [
        "data"
      ]
    },
    {
      "cells": [
        "{ \"_id\" : \"STR1001\",\"dsStringListOfValues1002\" : \"LIST 2\",\"dsStringListOfValues1003\" : \"LIST 1\",\"dsStringListOfValues1007\" : \"LIST 1\",\"dsStringListOfValues1008\" : \"LIST 1\",\"dsStringListOfValues1014\" : \"LIST 1\",\"dsStringListOfValues1015\" : \"LIST 1\"}"
      ]
    }
  ]
});
formatter.scenario({
  "name": "Add record to data service",
  "description": "",
  "keyword": "Scenario Outline"
});
formatter.before({
  "status": "passed"
});
formatter.before({
  "status": "passed"
});
formatter.before({
  "status": "passed"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "Data service \"string_ListOfValue\"",
  "keyword": "Given "
});
formatter.match({
  "location": "stepdefinitions.LoginToAppCenter.data_service(java.lang.String)"
});
formatter.result({
  "error_message": "java.lang.NullPointerException\r\n\tat pageModules.LoginAppCenter.dataService(LoginAppCenter.java:81)\r\n\tat stepdefinitions.LoginToAppCenter.data_service(LoginToAppCenter.java:56)\r\n\tat ✽.Data service \"string_ListOfValue\"(file:///C:/Users/DELL/eclipse-workspace/ds-dev-ui-automation-framework/testcases/string_ListOfValue.feature:65)\r\n",
  "status": "failed"
});
formatter.step({
  "name": "Add record \"{ \"_id\" : \"STR1001\",\"dsStringListOfValues1002\" : \"LIST 2\",\"dsStringListOfValues1003\" : \"LIST 1\",\"dsStringListOfValues1007\" : \"LIST 1\",\"dsStringListOfValues1008\" : \"LIST 1\",\"dsStringListOfValues1014\" : \"LIST 1\",\"dsStringListOfValues1015\" : \"LIST 1\"}\" to the data service",
  "keyword": "Then "
});
formatter.match({
  "location": "stepdefinitions.DesignTestCases.add_record(java.lang.String)"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "Expect error \"ID STR1001 already exists\" on save",
  "keyword": "And "
});
formatter.match({
  "location": "stepdefinitions.DesignTestCases.expect_error_on_save(java.lang.String)"
});
formatter.result({
  "status": "skipped"
});
formatter.scenarioOutline({
  "name": "Add record to data service",
  "description": "",
  "keyword": "Scenario Outline"
});
formatter.step({
  "name": "Data service \"string_ListOfValue\"",
  "keyword": "Given "
});
formatter.step({
  "name": "Add record \"\u003cdata\u003e\" to the data service",
  "keyword": "Then "
});
formatter.step({
  "name": "Expect error \"Unique check validation failed for dsStringListOfValues1002\" on save",
  "keyword": "And "
});
formatter.examples({
  "name": "",
  "description": "",
  "keyword": "Examples",
  "rows": [
    {
      "cells": [
        "data"
      ]
    },
    {
      "cells": [
        "{ \"_id\" : \"STR1003\",\"dsStringListOfValues1001\" : \"LIST 1\",\"dsStringListOfValues1002\" : \"LIST 1\",\"dsStringListOfValues1003\" : \"LIST 1\",\"dsStringListOfValues1007\" : \"LIST 1\",\"dsStringListOfValues1008\" : \"LIST 1\",\"dsStringListOfValues1014\" : \"LIST 1\",\"dsStringListOfValues1015\" : \"LIST 1\"}"
      ]
    }
  ]
});
formatter.scenario({
  "name": "Add record to data service",
  "description": "",
  "keyword": "Scenario Outline"
});
formatter.before({
  "status": "passed"
});
formatter.before({
  "status": "passed"
});
formatter.before({
  "status": "passed"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "Data service \"string_ListOfValue\"",
  "keyword": "Given "
});
formatter.match({
  "location": "stepdefinitions.LoginToAppCenter.data_service(java.lang.String)"
});
formatter.result({
  "error_message": "java.lang.NullPointerException\r\n\tat pageModules.LoginAppCenter.dataService(LoginAppCenter.java:81)\r\n\tat stepdefinitions.LoginToAppCenter.data_service(LoginToAppCenter.java:56)\r\n\tat ✽.Data service \"string_ListOfValue\"(file:///C:/Users/DELL/eclipse-workspace/ds-dev-ui-automation-framework/testcases/string_ListOfValue.feature:74)\r\n",
  "status": "failed"
});
formatter.step({
  "name": "Add record \"{ \"_id\" : \"STR1003\",\"dsStringListOfValues1001\" : \"LIST 1\",\"dsStringListOfValues1002\" : \"LIST 1\",\"dsStringListOfValues1003\" : \"LIST 1\",\"dsStringListOfValues1007\" : \"LIST 1\",\"dsStringListOfValues1008\" : \"LIST 1\",\"dsStringListOfValues1014\" : \"LIST 1\",\"dsStringListOfValues1015\" : \"LIST 1\"}\" to the data service",
  "keyword": "Then "
});
formatter.match({
  "location": "stepdefinitions.DesignTestCases.add_record(java.lang.String)"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "Expect error \"Unique check validation failed for dsStringListOfValues1002\" on save",
  "keyword": "And "
});
formatter.match({
  "location": "stepdefinitions.DesignTestCases.expect_error_on_save(java.lang.String)"
});
formatter.result({
  "status": "skipped"
});
formatter.scenarioOutline({
  "name": "Fetch record from the data service",
  "description": "",
  "keyword": "Scenario Outline"
});
formatter.step({
  "name": "Data service \"string_ListOfValue\"",
  "keyword": "Given "
});
formatter.step({
  "name": "Fetch record \"\u003cid\u003e\" from the data service",
  "keyword": "Then "
});
formatter.step({
  "name": "Match it to \"\u003cdata\u003e\"",
  "keyword": "And "
});
formatter.examples({
  "name": "",
  "description": "",
  "keyword": "Examples",
  "rows": [
    {
      "cells": [
        "id",
        "data"
      ]
    },
    {
      "cells": [
        "STR1001",
        "{\"_id\": \"STR1001\",\"dsStringListOfValues1001\": \"LIST 1\",\"dsStringListOfValues1002\": \"LIST 1\",\"dsStringListOfValues1003\": \"LIST 1\",\"dsStringListOfValues1004\": \"LIST 1\",\"dsStringListOfValues1005\": \"LIST 1\",\"dsStringListOfValues1007\": \"LIST 1\", \"dsStringListOfValues1008\": \"LIST 1\", \"dsStringListOfValues1010\": \"LIST 1\",  \"dsStringListOfValues1013\": \"LIST 1\", \"dsStringListOfValues1014\": \"LIST 1\", \"dsStringListOfValues1015\": \"LIST 1\",  \"dsStringListOfValues1018\": \"LIST 2\", \"dsStringListOfValues1020\":\"LIST 1\"}"
      ]
    }
  ]
});
formatter.scenario({
  "name": "Fetch record from the data service",
  "description": "",
  "keyword": "Scenario Outline"
});
formatter.before({
  "status": "passed"
});
formatter.before({
  "status": "passed"
});
formatter.before({
  "status": "passed"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "Data service \"string_ListOfValue\"",
  "keyword": "Given "
});
formatter.match({
  "location": "stepdefinitions.LoginToAppCenter.data_service(java.lang.String)"
});
formatter.result({
  "error_message": "java.lang.NullPointerException\r\n\tat pageModules.LoginAppCenter.dataService(LoginAppCenter.java:81)\r\n\tat stepdefinitions.LoginToAppCenter.data_service(LoginToAppCenter.java:56)\r\n\tat ✽.Data service \"string_ListOfValue\"(file:///C:/Users/DELL/eclipse-workspace/ds-dev-ui-automation-framework/testcases/string_ListOfValue.feature:84)\r\n",
  "status": "failed"
});
formatter.step({
  "name": "Fetch record \"STR1001\" from the data service",
  "keyword": "Then "
});
formatter.match({
  "location": "stepdefinitions.DesignTestCases.fetch_record_from_the_data_service(java.lang.String)"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "Match it to \"{\"_id\": \"STR1001\",\"dsStringListOfValues1001\": \"LIST 1\",\"dsStringListOfValues1002\": \"LIST 1\",\"dsStringListOfValues1003\": \"LIST 1\",\"dsStringListOfValues1004\": \"LIST 1\",\"dsStringListOfValues1005\": \"LIST 1\",\"dsStringListOfValues1007\": \"LIST 1\", \"dsStringListOfValues1008\": \"LIST 1\", \"dsStringListOfValues1010\": \"LIST 1\",  \"dsStringListOfValues1013\": \"LIST 1\", \"dsStringListOfValues1014\": \"LIST 1\", \"dsStringListOfValues1015\": \"LIST 1\",  \"dsStringListOfValues1018\": \"LIST 2\", \"dsStringListOfValues1020\":\"LIST 1\"}\"",
  "keyword": "And "
});
formatter.match({
  "location": "stepdefinitions.DesignTestCases.match_it_to(java.lang.String)"
});
formatter.result({
  "status": "skipped"
});
formatter.scenarioOutline({
  "name": "Update record to data service",
  "description": "",
  "keyword": "Scenario Outline"
});
formatter.step({
  "name": "Data service \"string_ListOfValue\"",
  "keyword": "Given "
});
formatter.step({
  "name": "Update record \"\u003cid\u003e\" with \"\u003cdata\u003e\" to the data service",
  "keyword": "Then "
});
formatter.examples({
  "name": "",
  "description": "",
  "keyword": "Examples",
  "rows": [
    {
      "cells": [
        "id",
        "data"
      ]
    },
    {
      "cells": [
        "STR1001",
        "{\"dsStringListOfValues1001\": \"LIST 2\",\"dsStringListOfValues1002\": \"LIST 2\",\"dsStringListOfValues1003\": \"LIST 2\",\"dsStringListOfValues1004\": \"LIST 2\",\"dsStringListOfValues1005\": \"LIST 2\",\"dsStringListOfValues1007\": \"LIST 2\", \"dsStringListOfValues1008\": \"LIST 2\", \"dsStringListOfValues1010\": \"LIST 2\",  \"dsStringListOfValues1013\": \"LIST 2\", \"dsStringText1014\": \"LIST 2\", \"dsStringListOfValues1015\": \"LIST 2\",  \"dsStringListOfValues1018\": \"LIST 2\"}"
      ]
    }
  ]
});
formatter.scenario({
  "name": "Update record to data service",
  "description": "",
  "keyword": "Scenario Outline"
});
formatter.before({
  "status": "passed"
});
formatter.before({
  "status": "passed"
});
formatter.before({
  "status": "passed"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "Data service \"string_ListOfValue\"",
  "keyword": "Given "
});
formatter.match({
  "location": "stepdefinitions.LoginToAppCenter.data_service(java.lang.String)"
});
formatter.result({
  "error_message": "java.lang.NullPointerException\r\n\tat pageModules.LoginAppCenter.dataService(LoginAppCenter.java:81)\r\n\tat stepdefinitions.LoginToAppCenter.data_service(LoginToAppCenter.java:56)\r\n\tat ✽.Data service \"string_ListOfValue\"(file:///C:/Users/DELL/eclipse-workspace/ds-dev-ui-automation-framework/testcases/string_ListOfValue.feature:93)\r\n",
  "status": "failed"
});
formatter.step({
  "name": "Update record \"STR1001\" with \"{\"dsStringListOfValues1001\": \"LIST 2\",\"dsStringListOfValues1002\": \"LIST 2\",\"dsStringListOfValues1003\": \"LIST 2\",\"dsStringListOfValues1004\": \"LIST 2\",\"dsStringListOfValues1005\": \"LIST 2\",\"dsStringListOfValues1007\": \"LIST 2\", \"dsStringListOfValues1008\": \"LIST 2\", \"dsStringListOfValues1010\": \"LIST 2\",  \"dsStringListOfValues1013\": \"LIST 2\", \"dsStringText1014\": \"LIST 2\", \"dsStringListOfValues1015\": \"LIST 2\",  \"dsStringListOfValues1018\": \"LIST 2\"}\" to the data service",
  "keyword": "Then "
});
formatter.match({
  "location": "stepdefinitions.DesignTestCases.update_record_with_to_the_data_service(java.lang.String,java.lang.String)"
});
formatter.result({
  "status": "skipped"
});
formatter.scenarioOutline({
  "name": "Fetch record from the data service",
  "description": "",
  "keyword": "Scenario Outline"
});
formatter.step({
  "name": "Data service \"string_ListOfValue\"",
  "keyword": "Given "
});
formatter.step({
  "name": "Fetch record \"\u003cid\u003e\" from the data service",
  "keyword": "Then "
});
formatter.step({
  "name": "Match it to \"\u003cdata\u003e\"",
  "keyword": "And "
});
formatter.examples({
  "name": "",
  "description": "",
  "keyword": "Examples",
  "rows": [
    {
      "cells": [
        "id",
        "data"
      ]
    },
    {
      "cells": [
        "STR1001",
        "{\"_id\": \"STR1001\",\"dsStringListOfValues1001\": \"LIST 2\",\"dsStringListOfValues1002\": \"LIST 1\",\"dsStringListOfValues1003\": \"LIST 2\",\"dsStringListOfValues1004\": \"LIST 1\",\"dsStringListOfValues1005\": \"LIST 2\",\"dsStringListOfValues1007\": \"LIST 1\", \"dsStringListOfValues1008\": \"LIST 2\", \"dsStringListOfValues1010\": \"LIST 1\",  \"dsStringListOfValues1013\": \"LIST 2\", \"dsStringListOfValues1014\": \"LIST 1\", \"dsStringListOfValues1015\": \"LIST 2\",  \"dsStringListOfValues1018\": \"LIST 2\",\"dsStringListOfValues1020\":\"LIST 1\"}"
      ]
    }
  ]
});
formatter.scenario({
  "name": "Fetch record from the data service",
  "description": "",
  "keyword": "Scenario Outline"
});
formatter.before({
  "status": "passed"
});
formatter.before({
  "status": "passed"
});
formatter.before({
  "status": "passed"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "Data service \"string_ListOfValue\"",
  "keyword": "Given "
});
formatter.match({
  "location": "stepdefinitions.LoginToAppCenter.data_service(java.lang.String)"
});
formatter.result({
  "error_message": "java.lang.NullPointerException\r\n\tat pageModules.LoginAppCenter.dataService(LoginAppCenter.java:81)\r\n\tat stepdefinitions.LoginToAppCenter.data_service(LoginToAppCenter.java:56)\r\n\tat ✽.Data service \"string_ListOfValue\"(file:///C:/Users/DELL/eclipse-workspace/ds-dev-ui-automation-framework/testcases/string_ListOfValue.feature:101)\r\n",
  "status": "failed"
});
formatter.step({
  "name": "Fetch record \"STR1001\" from the data service",
  "keyword": "Then "
});
formatter.match({
  "location": "stepdefinitions.DesignTestCases.fetch_record_from_the_data_service(java.lang.String)"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "Match it to \"{\"_id\": \"STR1001\",\"dsStringListOfValues1001\": \"LIST 2\",\"dsStringListOfValues1002\": \"LIST 1\",\"dsStringListOfValues1003\": \"LIST 2\",\"dsStringListOfValues1004\": \"LIST 1\",\"dsStringListOfValues1005\": \"LIST 2\",\"dsStringListOfValues1007\": \"LIST 1\", \"dsStringListOfValues1008\": \"LIST 2\", \"dsStringListOfValues1010\": \"LIST 1\",  \"dsStringListOfValues1013\": \"LIST 2\", \"dsStringListOfValues1014\": \"LIST 1\", \"dsStringListOfValues1015\": \"LIST 2\",  \"dsStringListOfValues1018\": \"LIST 2\",\"dsStringListOfValues1020\":\"LIST 1\"}\"",
  "keyword": "And "
});
formatter.match({
  "location": "stepdefinitions.DesignTestCases.match_it_to(java.lang.String)"
});
formatter.result({
  "status": "skipped"
});
formatter.scenarioOutline({
  "name": "Delete record from the data service",
  "description": "",
  "keyword": "Scenario Outline"
});
formatter.step({
  "name": "Data service \"string_ListOfValue\"",
  "keyword": "Given "
});
formatter.step({
  "name": "Delete record \"\u003cid\u003e\" from the data service",
  "keyword": "Then "
});
formatter.step({
  "name": "deleting from listing page",
  "keyword": "And "
});
formatter.examples({
  "name": "",
  "description": "",
  "keyword": "Examples",
  "rows": [
    {
      "cells": [
        "id"
      ]
    },
    {
      "cells": [
        "STR1001"
      ]
    }
  ]
});
formatter.scenario({
  "name": "Delete record from the data service",
  "description": "",
  "keyword": "Scenario Outline"
});
formatter.before({
  "status": "passed"
});
formatter.before({
  "status": "passed"
});
formatter.before({
  "status": "passed"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "Data service \"string_ListOfValue\"",
  "keyword": "Given "
});
formatter.match({
  "location": "stepdefinitions.LoginToAppCenter.data_service(java.lang.String)"
});
formatter.result({
  "error_message": "java.lang.NullPointerException\r\n\tat pageModules.LoginAppCenter.dataService(LoginAppCenter.java:81)\r\n\tat stepdefinitions.LoginToAppCenter.data_service(LoginToAppCenter.java:56)\r\n\tat ✽.Data service \"string_ListOfValue\"(file:///C:/Users/DELL/eclipse-workspace/ds-dev-ui-automation-framework/testcases/string_ListOfValue.feature:109)\r\n",
  "status": "failed"
});
formatter.step({
  "name": "Delete record \"STR1001\" from the data service",
  "keyword": "Then "
});
formatter.match({
  "location": "stepdefinitions.DesignTestCases.delete_record_from_the_data_service(java.lang.String)"
});
formatter.result({
  "status": "skipped"
});
formatter.step({
  "name": "deleting from listing page",
  "keyword": "And "
});
formatter.match({
  "location": "stepdefinitions.DesignTestCases.deleting_from_listing_page()"
});
formatter.result({
  "status": "skipped"
});
formatter.scenario({
  "name": "Log out of App Center",
  "description": "",
  "keyword": "Scenario"
});
formatter.before({
  "status": "passed"
});
formatter.before({
  "status": "passed"
});
formatter.before({
  "status": "passed"
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "User log out from AppCenter",
  "keyword": "Given "
});
formatter.match({
  "location": "stepdefinitions.DesignTestCases.user_logs_out_of_AppCenter()"
});
formatter.result({
  "error_message": "java.lang.NullPointerException\r\n\tat stepdefinitions.DesignTestCases.user_logs_out_of_AppCenter(DesignTestCases.java:209)\r\n\tat ✽.User log out from AppCenter(file:///C:/Users/DELL/eclipse-workspace/ds-dev-ui-automation-framework/testcases/string_ListOfValue.feature:117)\r\n",
  "status": "failed"
});
});