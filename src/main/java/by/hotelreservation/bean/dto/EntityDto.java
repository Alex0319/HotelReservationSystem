package by.hotelreservation.bean.dto;

import by.hotelreservation.bean.entity.AbstractEntity;

public class EntityDto extends AbstractEntity{
    protected String name;

    public EntityDto(){}

    public EntityDto(int id, String name){
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }

        EntityDto entityDto = (EntityDto) o;

        if (id != entityDto.id){
            return false;
        }
        return name != null ? name.equals(entityDto.name) : entityDto.name == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
