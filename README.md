# MultiLevelLibrary  
先上图  
<img width="250" height="400" alt="截屏1" src="https://github.com/JianxunMaster/MultiLevelLibrary/blob/master/image/5B1D355AA5E1DAFFC1CDE98A9F9E0E59.jpg"/>
<img width="250" height="400" alt="截屏2"
src="https://github.com/JianxunMaster/MultiLevelLibrary/blob/master/image/329A5551F8D475A0BAF7F2036B85046B.jpg"/>
## 简单使用
### 1.引用依赖库
#### 1）源码下载
下载压缩包解压到本地，导入开发工具
#### 2）快速导入
[![](https://jitpack.io/v/JianxunMaster/MultiLevelLibrary.svg)](https://jitpack.io/#JianxunMaster/MultiLevelLibrary)
### 2.在xml中使用ListView 
v1.0.0版还没有RecyclerView的适配  			  
### 3.数据bean继承MultiLevelModel使得相互嵌套  
	`
	public class ClassA extends MultiLevelModel
	`	  
### 4.适配器继承MultiLevelAdapter重写onCreateView方法  
	  `
	  public class MultiLevelTestAdapter extends MultiLevelAdapter
	  ` 
### 5.设置适配器
 	 `
        adapter = new MultiLevelTestAdapter(this, true, false
                , 1);
        adapter.setOnMultiLevelListener(new MultiLevelAdapter.OnMultiLevelListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id
                    , MultiLevelModel current, MultiLevelModel max) {
                Toast.makeText(MainActivity.this, "position = " + position + "" +
                                " , current level = " + current.getLevel() + " , outside level = "
                                + max.getLevel()
                        , Toast.LENGTH_SHORT).show();
            }
        });
        listView.setOnItemClickListener(adapter);
        listView.setAdapter(adapter);
        adapter.setList(list);
  	`
### 6.数据结构嵌套  
#### 建议实用fastjson解 
析数据格式，生成指定字段children集合，一句代码搞定
#### 示例数据如下：
	`
	for (int i = 0; i < 3; i++) {
            ClassA classA = new ClassA(i, " A" + i);
            classA.setChildren(new ArrayList());
            list.add(classA);
            count += 1;
            for (int j = 0; j < 3; j++) {
                ClassB classB = new ClassB(j, " A" + i + " B" + j);
                classA.getChildren().add(classB);
                count += 1;
                for (int k = 0; k < 3; k++) {
                    ClassC classC = new ClassC(" A" + i + " B" + j + " C" + k);
                    classB.getChildren().add(classC);
                    count += 1;
                    for (int l = 0; l < 3; l++) {
                        ClassD classD = new ClassD(" A" + i + " B" + j + " C" + k + " D" + l
                                , "D");
                        classC.getChildren().add(classD);
                        count += 1;
                    }
                }
            }
        }
	`
## QQ群464635057 欢迎加群讨论 扫码加群
<div align=center><img alt="群二维码" src="https://github.com/JianxunMaster/MultiLevelLibrary/blob/master/image/MultiLevelLibrary%E8%AE%A8%E8%AE%BA%E7%BE%A4%E7%BE%A4%E4%BA%8C%E7%BB%B4%E7%A0%81.png"/></div>
## 大哥大姐觉得好用的打点赏，一点心意作鼓励
<div align=center>
<img width="200" height="300" alt="支付宝扫码打赏" src="https://github.com/JianxunMaster/MultiLevelLibrary/blob/master/image/80377AD8D54B09D5574FFF0DA75B61F7.png"/>  
<img width="200" height="300" alt="微信扫码打赏" src="https://github.com/JianxunMaster/MultiLevelLibrary/blob/master/image/C1D99830DC2142387181396BF25B1C59.png"/>
</div>
## 此库持续更新中···加油~~~~(>_<)~~~~
