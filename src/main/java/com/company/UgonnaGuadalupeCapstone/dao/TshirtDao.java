package com.company.UgonnaGuadalupeCapstone.dao;

import com.company.UgonnaGuadalupeCapstone.model.Tshirt;
import java.util.List;

public interface TshirtDao {

    Tshirt addTshirt (Tshirt tshirt); //create
    Tshirt getTshirt (int id);//read
    List<Tshirt> getAllTshirt();//read all

    void updateTshirt(Tshirt tshirt); //update
    void deleteTshirt(int id);//delete
    List<Tshirt> getTshirtByColor(String color);//By Color
    List<Tshirt> getTshirtBySize(String size);//By Size

}
