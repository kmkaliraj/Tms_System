package oracle.adf.bean;

//import javax.faces.context.FacesContext;

import javax.faces.context.FacesContext;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCDataControl;
import oracle.adf.model.binding.DCIteratorBinding;

import oracle.adf.view.rich.component.rich.data.RichTable;


import oracle.jbo.domain.Number;
import oracle.jbo.Key;
import oracle.jbo.ViewObject;
import oracle.jbo.uicli.binding.JUCtrlHierBinding;

import oracle.jbo.uicli.binding.JUCtrlHierNodeBinding;

import oracle.tms.offencemanager.model.appmodule.TrafficManagerAMImpl;

import org.apache.myfaces.trinidad.event.SelectionEvent;
import org.apache.myfaces.trinidad.model.CollectionModel;


public class OffeneceSelectionBean {
    
   Number offenceId;
  
    
    public OffeneceSelectionBean() {
    }
    
   /* public void showPopup(){
      FacesContext context = FacesContext.getCurrentInstance();
           BindingContext bindingContext = BindingContext.getCurrent();
            DCDataControl dc  = bindingContext.findDataControl("TrafficManagerAMDataControl"); 
            TrafficManagerAMImpl appM = (TrafficManagerAMImpl)dc.getDataProvider();

            ViewObject vo = appM.findViewObject("QueryOffence1");
            vo.setNamedWhereClauseParam("p_offence_id", offenceId);      
            vo.executeQuery();
            
    }*/

    public void getSelectedOffence(SelectionEvent selectionEvent) {
      this.onTableRowSelection(selectionEvent);
      DCBindingContainer bindings = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
      DCIteratorBinding iteratorBinding = bindings.findIteratorBinding("TmOffenceDetails3Iterator");
      offenceId =  (Number) iteratorBinding.getCurrentRow().getAttribute("OffenceId");
      System.out.println("Offence ID :" + this.getOffenceId());
      
    /*  FacesContext context = FacesContext.getCurrentInstance();
      BindingContext bindingContext = BindingContext.getCurrent();
      DCDataControl dc  = bindingContext.findDataControl("TrafficManagerAMDataControl"); 
      TrafficManagerAMImpl appM = (TrafficManagerAMImpl)dc.getDataProvider();

      ViewObject vo = appM.findViewObject("QueyOffence1");
      vo.setNamedWhereClauseParam("p_offence_id", offenceId);    
     showPopup(vo.executeQuery()); */      
    }
    
    public void onTableRowSelection(SelectionEvent selectionEvent){
      RichTable tab = (RichTable) selectionEvent.getSource();
      CollectionModel model = (CollectionModel) tab.getValue();
      JUCtrlHierBinding binding1 = (JUCtrlHierBinding)model.getWrappedData();
      DCIteratorBinding iteratorBinding = (DCIteratorBinding) binding1.getDCIteratorBinding();
      Object selectedRowData = tab.getSelectedRowData(); 
      JUCtrlHierNodeBinding node = (JUCtrlHierNodeBinding) selectedRowData;
      Key  rowKey = node.getRowKey();
      iteratorBinding.setCurrentRowWithKey(rowKey.toStringFormat(true));
    }

    public void setOffenceId(Number offenceId) {
        this.offenceId = offenceId;
    }

    public Number getOffenceId() {
        return offenceId;
    }

    public void newMethod() {
      FacesContext context = FacesContext.getCurrentInstance();
           BindingContext bindingContext = BindingContext.getCurrent();
            DCDataControl dc  = bindingContext.findDataControl("TrafficManagerAMDataControl"); 
            TrafficManagerAMImpl appM = (TrafficManagerAMImpl)dc.getDataProvider();

            ViewObject vo = appM.findViewObject("QueryOffence1");
            vo.setNamedWhereClauseParam("P_offence_id", offenceId);      
            vo.executeQuery();
    }
}
