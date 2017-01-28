package oracle.adf.bean;




import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;


import oracle.adf.model.BindingContext;
import oracle.adf.model.DataControlFrame;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.output.RichOutputText;


import oracle.binding.BindingContainer;

import oracle.binding.OperationBinding;

import oracle.jbo.*;
import oracle.jbo.client.Configuration;
import oracle.jbo.domain.*;
import oracle.jbo.domain.Number;

public class AddOffenceBean {
    
  private RichInputText VehNo;
  private String VehicleNumber;
  private String OffenceID;
  private DBSequence OffenceDetailID;
    


    
    public AddOffenceBean() {
    }

    public void actionListener(ActionEvent actionEvent) {
      RichOutputText VehNo = (RichOutputText)FacesContext.getCurrentInstance().getViewRoot().findComponent("ot10");
      //it1 is the id of input text field
              String useNo = (String)VehNo.getValue();              
              setVehicleNumber(useNo);
    }


    public void setVehNo(RichInputText VehNo) {
        this.VehNo = VehNo;
    }

    public RichInputText getVehNo() {
        return VehNo;
    }

    public void setVehicleNumber(String VehicleNumber) {
        this.VehicleNumber = VehicleNumber;
    }

    public String getVehicleNumber() {
        return VehicleNumber;
    }

    public void commit(ActionEvent actionEvent) {
           BindingContext bc = BindingContext.getCurrent();
           String dcfName = bc.getCurrentDataControlFrame();
           DataControlFrame dcf = bc.findDataControlFrame(dcfName);
           dcf.commit();
                       
           
         
    }

    public void rollback(ActionEvent actionEvent) {
      BindingContext bc = BindingContext.getCurrent();
         String dcfName = bc.getCurrentDataControlFrame();
         DataControlFrame dcf = bc.findDataControlFrame(dcfName);
         if (dcf.isTransactionDirty()) {
            dcf.rollback();
            }
}

    public void setOffenceId(ActionEvent actionEvent) {
        RichOutputText OffID = (RichOutputText)FacesContext.getCurrentInstance().getViewRoot().findComponent("cl1");
        // //it1 is the id of input text field
                String useID = (String)OffID.getValue();              
                setOffenceID(useID); 
    }

    public void setOffenceID(String OffenceID) {
        this.OffenceID = OffenceID;
    }

    public String getOffenceID() {
        return OffenceID;
    }

    public void AddCriteria(ValueChangeEvent valueChangeEvent) {
        String status = (String)valueChangeEvent.getNewValue();
        System.out.println("New Value: "+ status);
        BindingContext bcx = BindingContext.getCurrent();
        BindingContainer bc = bcx.getCurrentBindingsEntry();
        OperationBinding bo = bc.getOperationBinding("searchPendingOffences");
        
        
       bo.getParamsMap().put("status",status);
       bo.execute();

    }

    public void setOffenceDetailID(DBSequence OffenceDetailID) {
        this.OffenceDetailID = OffenceDetailID;
    }

    public DBSequence getOffenceDetailID() {
        return OffenceDetailID;
    }
}
