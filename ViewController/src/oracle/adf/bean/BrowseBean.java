package oracle.adf.bean;

import org.apache.myfaces.trinidad.event.AttributeChangeEvent;
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

public class BrowseBean {
    public BrowseBean() {
    }

 private Number offenceId;
 
      
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

    public void selectOffence(SelectionEvent selectionEvent) {
      this.onTableRowSelection(selectionEvent);
      DCBindingContainer bindings = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
      DCIteratorBinding iteratorBinding = bindings.findIteratorBinding("TmOffenceDetails1Iterator");
      offenceId =  (Number) iteratorBinding.getCurrentRow().getAttribute("OffenceId");
      System.out.println("Offence ID :" + this.offenceId);
      
      /*  FacesContext context = FacesContext.getCurrentInstance();
      BindingContext bindingContext = BindingContext.getCurrent();
      DCDataControl dc  = bindingContext.findDataControl("TrafficManagerAMDataControl"); 
      TrafficManagerAMImpl appM = (TrafficManagerAMImpl)dc.getDataProvider();

      ViewObject vo = appM.findViewObject("QueyOffence1");
      vo.setNamedWhereClauseParam("p_offence_id", offenceId);    
      showPopup(vo.executeQuery()); */

    }


    public String getDetails() {       
            System.out.println("I'm here");
            FacesContext context = FacesContext.getCurrentInstance();
            BindingContext bindingContext = BindingContext.getCurrent();
            DCDataControl dc  = bindingContext.findDataControl("BrowseOffenseAMDataControl"); 
            TrafficManagerAMImpl appM = (TrafficManagerAMImpl)dc.getDataProvider();
            System.out.println("I'm here");
            ViewObject vo = appM.findViewObject("QueryOffence1");
            vo.setNamedWhereClauseParam("P_offence_id", this.offenceId);    
            vo.executeQuery();
            
        return null;
    }

    public void setOffenceId(Number offenceId) {
        this.offenceId = offenceId;
    }

    public Number getOffenceId() {
        return offenceId;
    }
}
