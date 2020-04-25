

package Item;



import java.util.ArrayList;


public class ItemList {


	private ArrayList<Item> items;
	

	public ItemList(){
		
		items = new ArrayList<Item>();

	}


	public void addItem(Item toAdd){

		items.add(toAdd);

	}


	public Item takeItem(int index){

		Item ret;

		if(index < 0 || index >= items.size()) return null;
		
		ret = items.get(index);
		items.remove(index);
		
		return ret;

	}

	public Item getItem(int index){

		if(index < 0 || index >= items.size()) return null;
		
		return items.get(index);

	}

	public ArrayList<Item> getItems(){
		return items;
	}

    
	// public Double getItemWeight(int index){

	// 	if(index < 0 || index >= items.size()) return 0.0;

	// 	return items.get(index).getWeight();
	// }

	// public Double getAllWeight(){

	// 	Double toRet = new Double(0.0);

	// 	for (int i = 0; i < items.size(); ++i) {
	// 		toRet += items.get(i).getWeight();
	// 	}

	// 	return toRet;

	// }

}