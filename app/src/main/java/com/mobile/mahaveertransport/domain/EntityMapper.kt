package  com.mobile.mahaveertransport.domain

// Helper class used to map Network model to domain model
interface EntityMapper<NetworkModel, DomainModel> {
    fun transformFrom(networkDataModel: NetworkModel): DomainModel
}